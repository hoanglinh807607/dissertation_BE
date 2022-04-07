package com.dissertation.userservice.service.impl;

import com.dissertation.common.entities.user_service.Feature;
import com.dissertation.common.entities.user_service.Menu;
import com.dissertation.common.entities.user_service.Role;
import com.dissertation.common.enums.RoleCode;
import com.dissertation.common.exception.ClientException;
import com.dissertation.common.model.user_service.FeatureModel;
import com.dissertation.common.model.user_service.MenuModel;
import com.dissertation.common.model.user_service.RoleModel;
import com.dissertation.common.model.user_service.role.PostMenuRequest;
import com.dissertation.common.model.user_service.role.PostRoleRequest;
import com.dissertation.common.model.user_service.role.PutRoleRequest;
import com.dissertation.common.model.user_service.role.RoleRequestParams;
import com.dissertation.common.security.UserDetailsImpl;
import com.dissertation.userservice.repository.FeatureRepository;
import com.dissertation.userservice.repository.MenuRepository;
import com.dissertation.userservice.repository.RoleRepository;
import com.dissertation.userservice.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    FeatureRepository featureRepository;

    @Override
    public List<RoleModel> getRoleTemplate() {
        List<RoleModel> roleModels = findByCodeIn(
                Arrays.asList(RoleCode.SUPER_ADMIN.toString(), RoleCode.ADMIN.toString(), RoleCode.USER.toString()))
                .stream().filter(roleModel -> {
                    return roleModel.getRoleId() == 1 || roleModel.getRoleId() == 2 || roleModel.getRoleId() == 3;
                }).collect(Collectors.toList());
        roleModels.forEach(template -> {
            List<MenuModel> menuModels = this.menuRepository.findByRoleId(template.getRoleId())
                    .stream().map(MenuModel::of).collect(Collectors.toList());
            menuModels.forEach(menuModel -> menuModel.setFeatureModel(this.getFeatureById(menuModel.getFeatureId())));
            template.setMenuModels(menuModels);
        });
        return roleModels;
    }

    private List<RoleModel> findByCodeIn(List<String> codes) {
        return this.roleRepository.findByCodeIn(codes).stream().map(RoleModel::of).collect(Collectors.toList());
    }

    @Override
    public RoleModel getRole(Integer roleId) {
        Optional<Role> optionalRole = this.roleRepository.findById(roleId);
        if (!optionalRole.isPresent()) {
            return null;
        }
        List<Menu> menus = this.menuRepository.findByRoleId(roleId);
        RoleModel roleModel = RoleModel.of(optionalRole.get());
        roleModel.setMenuModels(menus.stream().map(MenuModel::of).collect(Collectors.toList()));
        return roleModel;
    }

    @Override
    public RoleModel findByName(String name) {
        Optional<Role> role = this.roleRepository.findByName(name);
        if (!role.isPresent()) {
            return null;
        }
        return RoleModel.of(role.get());
    }

    @Override
    @Transactional
    public RoleModel createRole(PostRoleRequest request) {
        RoleModel roleModel = saveRole(request);
        List<MenuModel> menuModels = new ArrayList<>();
        if (request.getPermissions() != null ) {
            request.getPermissions().forEach(permission -> menuModels.add(this.createMenuPermission(roleModel.getRoleId(), permission)));
            roleModel.setMenuModels(menuModels);
        }
        return roleModel;
    }

    @Override
    @Transactional
    public RoleModel updateRole(Integer id, PutRoleRequest request) {
        RoleModel roleModel = editRole(id, request);
        List<MenuModel> menuModels = updateMenuPermission(id, request);
        roleModel.setMenuModels(menuModels);
        return roleModel;
    }

    @Override
    public Page<RoleModel> findAll(RoleRequestParams params) {
        Sort sort;
        if ("ASC".equalsIgnoreCase(params.getDirection())) {
            sort = Sort.by(params.getSortBy()).ascending();
        } else {
            sort = Sort.by(params.getSortBy()).descending();
        }
        Pageable paging = PageRequest.of(params.getPageNo(), params.getPageSize(), sort);
        String keyword = StringUtils.isEmpty(params.getKeyword()) ? StringUtils.EMPTY : params.getKeyword();
        if (params.getStatus() == null) {
            Page<RoleModel> roleModels = this.roleRepository.findAll(keyword, paging).map(RoleModel::of);
            roleModels.forEach(roleModel -> {
                boolean isCanDelete = this.roleRepository.canDeleteOrDisable(roleModel.getRoleId());
                roleModel.setCanDeleteOrDisable(isCanDelete);
            });
            return roleModels;
        }
        Page<RoleModel> roleModels = this.roleRepository.findByStatus(keyword, params.getStatus(), paging).map(RoleModel::of);
        roleModels.forEach( roleModel -> {
            boolean isCanDelete = this.roleRepository.canDeleteOrDisable(roleModel.getRoleId());
            roleModel.setCanDeleteOrDisable(isCanDelete);
        });
        return roleModels;
    }

    @Override
    public RoleModel findById(Integer id) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        if (!optionalRole.isPresent()) {
            return null;
        }
        return RoleModel.of(optionalRole.get());
    }

    @Override
    public RoleModel enableRole(Integer id) {
        return enableOrDisableRole(id, true);
    }

    @Override
    public RoleModel disableRole(Integer id) {
        return enableOrDisableRole(id, false);
    }

    @Override
    public RoleModel deleteRole(Integer id) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        if (!optionalRole.isPresent()) {
            return null;
        }
        Role role = optionalRole.get();
        role.setIsDeleted(true);
        role.setDeleteAt(new Timestamp(System.currentTimeMillis()));
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        role.setDeleteBy(userDetails.getFullName());
        return RoleModel.of(this.roleRepository.save(role));
    }

    @Override
    public Boolean canDeleteOrDisable(Integer roleId) {
        return this.roleRepository.canDeleteOrDisable(roleId);
    }

    private RoleModel saveRole(PostRoleRequest request) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role role = request.fillRoleEntity();
//        role.setCreateAt(new Timestamp(System.currentTimeMillis()));
//        role.setCreateBy(userDetails.getFullName());
        return RoleModel.of(this.roleRepository.save(role));
    }

    //Create Menu wth roleId, featureId and action(view, create, update, delete)
    private MenuModel createMenuPermission(Integer roleId, PostMenuRequest request) {
        Menu menu = request.fillMenuEntity();
        menu.setRoleId(roleId);
        MenuModel menuModel = MenuModel.of(this.menuRepository.save(menu));
        if (menuModel.getFeatureId() != null) {
            menuModel.setFeatureModel(FeatureModel.of(featureRepository.findById(menuModel.getFeatureId()).get()));
        }
        return menuModel;
    }


    private RoleModel editRole(Integer roleId, PutRoleRequest request) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Role> optionalRole = this.roleRepository.findById(roleId);
        if (!optionalRole.isPresent()) {
            log.error("Role ID [{}] not found", roleId);
            throw  new ClientException("role not found.");
        }
        Role role = request.updateRoleEntity(optionalRole.get());
//        role.setUpdateAt(new Timestamp(System.currentTimeMillis()));
//        role.setUpdateBy(userDetails.getFullName());
        return RoleModel.of(this.roleRepository.save(role));
    }

    private List<MenuModel> updateMenuPermission(Integer roleId, PutRoleRequest request) {
        List<MenuModel> menuModels = new ArrayList<>();
        List<Menu> menus = this.menuRepository.findByRoleId(roleId); //Get List Menu from RoleId
        List<PostMenuRequest> permissionRequest = request.getPermissions(); //List<PostMenuRequest>
        if (permissionRequest != null) {
            //With list new menus. We will get List menu from DB and Check every feature
            permissionRequest.forEach(permission -> {   //PostMenuRequest -> feature, action
                Optional<Menu> optionalMenu = getMenu(roleId, permission.getFeatureId(), menus);
                // If exist rolePermission in DB => update
                if (optionalMenu.isPresent()) {
                    MenuModel menuModel = MenuModel.of(this.menuRepository.save(permission.updateMenuEntity(optionalMenu.get())));
                    if (menuModel.getFeatureId() != null) {
                        menuModel.setFeatureModel(FeatureModel.of(featureRepository.findById(menuModel.getFeatureId()).get()));
                    }
                    menuModels.add(menuModel);
                } else {
                    menuModels.add(createMenuPermission(roleId, permission));
                }
            });
        }
        return menuModels;
    }

    private Optional<Menu> getMenu(Integer roleId, Integer featureId, List<Menu> menus) {
        return menus.stream().filter(menu -> {
            return menu.getRoleId().equals(roleId) && menu.getFeatureId().equals(featureId);
        }).findFirst();
    }

    private RoleModel enableOrDisableRole(Integer id, boolean enable) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setStatus(enable);
            role.setUpdateAt(new Timestamp(System.currentTimeMillis()));
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            role.setUpdateBy(userDetails.getFullName());
            return RoleModel.of(this.roleRepository.save(role));
        }
        return null;
    }

    private FeatureModel getFeatureById(Integer featureId) {
        Optional<Feature> feature = this.featureRepository.findById(featureId);
        if (feature.isPresent()) {
            return FeatureModel.of(feature.get());
        }
        return null;
    }
}
