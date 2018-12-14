package com.dage.service;

import com.dage.dao.PermissionDao;
import com.dage.entity.Emp;
import com.dage.entity.Permission;
import com.dage.entity.Role;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className:PermissionServiceImpl
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-07 18:48
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 根据角色信息获取对应权限
     * @param
     * @return
     */
    @Override
    public List<Permission> getListByRole() {
        //session.getAttribute()
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp)subject.getPrincipal();
        List<Permission> permissionList = permissionDao.getListByRole(emp.getRoleid());
        List<Permission> list = new ArrayList<>();
        if (permissionList!=null&&permissionList.size()>0){
            for (Permission permission : permissionList) {
                if (permission.getPid()==0){
                    list.add(permission);
                    bindChirldren(permission,permissionList);
                }
            }
        }
        return list;
    }

    /**
     * 根据角色信息获取对应权限令牌
     * @param
     * @return
     */
    public List<Permission> getListByRoleid() {
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp)subject.getPrincipal();
        return permissionDao.getListByRole(emp.getRoleid());
    }
    /**
     * 获取选中的权限
     * @param roleid
     * @return
     */
    @Override
    public List<Permission> getCheckList(Integer roleid) {
        List<Permission> listByRole = permissionDao.getListByRole(roleid);
        return listByRole;
    }

    /**
     * 权限添加
     * @param map
     * @return
     */
    @Override
    public int add(Map map) {
        if (map.get("pid")==null||map.get("pid")=="null"||map.get("pid")==""){
            map.put("pid","0");
        }
        return permissionDao.add(map);
    }

    @Override
    public List<Permission> getList() {
        return permissionDao.getList();
    }

    @Override
    public int saveRolePower(Role role) {
        SqlSession sqlSession = null;
        Boolean flag = true;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            PermissionDao permissionDaos = sqlSession.getMapper(PermissionDao.class);
            String powersid = role.getPowersid();
            Integer roleid = role.getRoleid();
            if (powersid==""||powersid==null||powersid=="null"){
                int del = permissionDao.delRolePower(roleid);
                return del;
            }
            String[] powerids = powersid.split(",");
            permissionDao.delRolePower(roleid);
            for (String powerid : powerids) {
                int i = permissionDaos.saveRolePower(roleid,Integer.valueOf(powerid));
                if (i==0) { flag = false;}
            }
            System.out.println(flag);
            if(flag) {
                sqlSession.commit();
                return 1;
            }else{
                sqlSession.rollback();
                return 0;
            }
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Permission getPowerById(Integer id) {
        return permissionDao.getPowerById(id);
    }

    @Override
    public int update(Map map) {
        return permissionDao.update(map);
    }

    @Override
    public int del(Integer id) {
        int i = permissionDao.delPowerRole(id);
        if (i>0)
            return permissionDao.del(id);
        return 0;
    }

    /**
     * 递归绑定所有子节点
     * @param parentTreeNode
     * @param powerAllList
     */
    private  void bindChirldren(Permission parentTreeNode, List<Permission> powerAllList ){
        for(Permission chirldrenTreeNode:powerAllList){
            if(parentTreeNode.getId()==chirldrenTreeNode.getPid()){
                //获取当前节点的所有子节点集合
                List<Permission> children = parentTreeNode.getChildren();
                if(children==null){//如果孩子节点为空,
                    List<Permission> childrenTempList = new ArrayList<Permission>();//实例化孩子集合
                    childrenTempList.add(chirldrenTreeNode);//添加子节点到集合里面
                    parentTreeNode.setChildren(childrenTempList);//设置孩子集合
                }else{//不空，说明设置过
                    children.add(chirldrenTreeNode);//添加子节点到集合里面
                }
                //自己调用自己,找孩子
                bindChirldren(chirldrenTreeNode,powerAllList);
            }
        }
    }
}
