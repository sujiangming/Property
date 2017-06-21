package com.jdry.property.bean;

import com.alibaba.fastjson.JSON;
import com.jdry.property.config.Config;
import com.jdry.property.util.JdryPersistence;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JDRY_SJM on 2017/4/21.
 */

public class LoginBean implements Serializable {

    /**
     * data : {"auth":[{"name":"公告管理","orderNo":"1","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"01","urlName":"../notice/page_approve_list.html"},{"name":"咨询建议","orderNo":"2","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"02","urlName":"../suggest/suggest_list_win.html"},{"name":"维修服务","orderNo":"3","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"03","urlName":"../property/property_dispatch.html"},{"name":"收费管理","orderNo":"4","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"04","urlName":"../pay/pay_win.html"},{"name":"活动管理","orderNo":"5","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"05","urlName":"../activity/activity_win.html"},{"name":"论坛管理","orderNo":"6","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"06","urlName":"../forum/forum_win.html"},{"name":"家政服务","orderNo":"7","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"07","urlName":"../housekeeping/housekeeping_win.html"},{"name":"巡视巡检","orderNo":"8","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"08","urlName":"../patrol/patrol.html"},{"name":"移动监控","orderNo":"9","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"09","urlName":"../monitor/monitor_win.html"},{"name":"人员定位","orderNo":"10","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"10","urlName":"../location/location_win.html"},{"name":"访客管理","orderNo":"11","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"11","urlName":"../visitor/visitor_win.html"},{"name":"GIS超级管理员","orderNo":"12","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"12","urlName":""},{"name":"GIS管理员","orderNo":"13","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"13","urlName":""},{"name":"GIS建模人员","orderNo":"14","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"14","urlName":""},{"name":"GIS用户","orderNo":"15","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"15","urlName":""}],"bbsFlag":0,"cName":"系统管理员","mobile":"18917888888","positionId":"001","saved":false,"userName":"admin"}
     * message : 登录成功！
     * saved : false
     * status : 1
     * token : AC8B69263850EC121816999ABE9956A53A9BE7A1C5257657C4236FC1
     */

    private DataBean data;
    private String message;
    private boolean saved;
    private int status;
    private String token;

    private static final long serialVersionUID = 1L;
    private volatile static LoginBean instance = null;//volatile关键字来保证其线程间的可见性

    private LoginBean() {
    }

    public static LoginBean getInstance() {
        if (instance == null) {
            synchronized (LoginBean.class) {
                if (instance == null) {
                    instance = new LoginBean();
                }
            }
        }
        return instance;
    }

    public void setValueByLoginDataJson(LoginBean json) {
        this.status = json.getStatus();
        this.message = json.getMessage();
        this.token = json.getToken();
        this.saved = json.isSaved();
        this.data = json.getData();
        save();  // 同步更新
    }

    public void load() {  // 加载本地数据
        try {
            String loginBeanStr = JdryPersistence.getObject(Config.LOGIN_INFO_SERIALIZE_KEY);
            if (null == loginBeanStr || "".equals(loginBeanStr)) {
                return;
            }
            LoginBean bean = JSON.parseObject(loginBeanStr, LoginBean.class);
            this.status = bean.getStatus();
            this.token = bean.getToken();
            this.data = bean.getData();
            this.message = bean.getMessage();
            this.saved = bean.isSaved();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearData() {
        this.getData().setUserName(null);
        this.setToken(null);
        save(); // 保存本地数据
    }

    public void save() {
        try {
            JdryPersistence.saveObject(JSON.toJSONString(this), Config.LOGIN_INFO_SERIALIZE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean implements Serializable {
        /**
         * auth : [{"name":"公告管理","orderNo":"1","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"01","urlName":"../notice/page_approve_list.html"},{"name":"咨询建议","orderNo":"2","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"02","urlName":"../suggest/suggest_list_win.html"},{"name":"维修服务","orderNo":"3","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"03","urlName":"../property/property_dispatch.html"},{"name":"收费管理","orderNo":"4","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"04","urlName":"../pay/pay_win.html"},{"name":"活动管理","orderNo":"5","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"05","urlName":"../activity/activity_win.html"},{"name":"论坛管理","orderNo":"6","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"06","urlName":"../forum/forum_win.html"},{"name":"家政服务","orderNo":"7","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"07","urlName":"../housekeeping/housekeeping_win.html"},{"name":"巡视巡检","orderNo":"8","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"08","urlName":"../patrol/patrol.html"},{"name":"移动监控","orderNo":"9","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"09","urlName":"../monitor/monitor_win.html"},{"name":"人员定位","orderNo":"10","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"10","urlName":"../location/location_win.html"},{"name":"访客管理","orderNo":"11","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"11","urlName":"../visitor/visitor_win.html"},{"name":"GIS超级管理员","orderNo":"12","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"12","urlName":""},{"name":"GIS管理员","orderNo":"13","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"13","urlName":""},{"name":"GIS建模人员","orderNo":"14","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"14","urlName":""},{"name":"GIS用户","orderNo":"15","roleId":"41f74322-7e68-4f6c-9350-901d717b73c6","saved":false,"urlId":"15","urlName":""}]
         * bbsFlag : 0
         * cName : 系统管理员
         * mobile : 18917888888
         * positionId : 001
         * saved : false
         * userName : admin
         */

        private int bbsFlag;
        private String cName;
        private String mobile;
        private String positionId;
        private boolean saved;
        private String userName;
        private String deptName;
        private String positionName;
        private List<AuthBean> auth;

        public int getBbsFlag() {
            return bbsFlag;
        }

        public void setBbsFlag(int bbsFlag) {
            this.bbsFlag = bbsFlag;
        }

        public String getCName() {
            return cName;
        }

        public void setCName(String cName) {
            this.cName = cName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public boolean isSaved() {
            return saved;
        }

        public void setSaved(boolean saved) {
            this.saved = saved;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getcName() {
            return cName;
        }

        public void setcName(String cName) {
            this.cName = cName;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public List<AuthBean> getAuth() {
            return auth;
        }

        public void setAuth(List<AuthBean> auth) {
            this.auth = auth;
        }

        public static class AuthBean implements Serializable {
            /**
             * name : 公告管理
             * orderNo : 1
             * roleId : 41f74322-7e68-4f6c-9350-901d717b73c6
             * saved : false
             * urlId : 01
             * urlName : ../notice/page_approve_list.html
             */

            private String name;
            private String orderNo;
            private String roleId;
            private boolean saved;
            private String urlId;
            private String urlName;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public boolean isSaved() {
                return saved;
            }

            public void setSaved(boolean saved) {
                this.saved = saved;
            }

            public String getUrlId() {
                return urlId;
            }

            public void setUrlId(String urlId) {
                this.urlId = urlId;
            }

            public String getUrlName() {
                return urlName;
            }

            public void setUrlName(String urlName) {
                this.urlName = urlName;
            }
        }
    }
}
