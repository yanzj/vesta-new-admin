package com.maxrocky.vesta.sso;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class ConnnectDbTools {
    private Connection conn = null;
    private Map map = new HashMap();
    private int psExDays = -1;

    ConnnectDbTools() {
    }

    public Connection getConnection() {
        if(this.conn != null) {
            return this.conn;
        } else if(SsoClientUtils.CONNECTION_PROVIDER_NAME != null && SsoClientUtils.CONNECTION_PROVIDER_NAME.length() != 0) {
            try {
                Class.forName(SsoClientUtils.CONNECTION_PROVIDER_NAME).newInstance();
                StringBuffer e = new StringBuffer("jdbc:sqlserver://");
                e.append(SsoClientUtils.CONNECTION_DATASOURCE);
                e.append(";DatabaseName=");
                e.append(SsoClientUtils.CONNECTION_DBNAME);
                this.conn = DriverManager.getConnection(e.toString(), SsoClientUtils.CONNECTION_USER, SsoClientUtils.CONNECTION_PASSWORD);
            } catch (InstantiationException var2) {
                SsoLogger.error(var2);
            } catch (IllegalAccessException var3) {
                SsoLogger.error(var3);
            } catch (ClassNotFoundException var4) {
                SsoLogger.error(var4);
            } catch (SQLException var5) {
                SsoLogger.error(var5);
            }

            return this.conn;
        } else {
            return null;
        }
    }

    public String getCookiesExpireTime() {
        if(this.getConnection() == null) {
            return "24:00:00";
        } else {
            String temp = "24:00:00";

            try {
                String e = "SELECT PolicyID, PolicyName, DisplayName, PolicyRule, Comment FROM TP_Policy WHERE PolicyName = \'expireTime\'";
                Statement stmt = this.conn.createStatement();

                ResultSet rs;
                for(rs = stmt.executeQuery(e); rs.next(); temp = rs.getString("PolicyRule")) {
                    ;
                }

                temp = temp.substring(temp.indexOf("#") + 1);
                rs.close();
                stmt.close();
            } catch (SQLException var13) {
                SsoLogger.error(var13);
            } finally {
                if(this.conn != null) {
                    try {
                        this.conn.close();
                    } catch (SQLException var12) {
                        SsoLogger.error(var12);
                    }
                }

            }

            return temp;
        }
    }

    public int getUserDbStatus(String username, String pwd) throws Exception {
        if(this.getConnection() == null) {
            return 3;
        } else {
            byte flag = 3;

            try {
                String e = "Select PwdLastSet, UserAccountControl, PwdExpires, Pwd From TF_User Where UserID=\'" + username + "\'";
                PreparedStatement ps = this.conn.prepareStatement(e);
                ResultSet rs = ps.executeQuery();
                String lastSet = null;
                long acountControl = 0L;
                String pwdExpires = null;
                String dbPwd = null;
                byte var18;
                if(rs.next()) {
                    lastSet = rs.getString(1);
                    acountControl = (long)rs.getInt(2);
                    pwdExpires = rs.getString(3);
                    dbPwd = rs.getString(4);
                    if((acountControl & 2L) == 2L) {
                        flag = 1;
                        var18 = flag;
                        return var18;
                    } else {
                        ShaTools sha = new ShaTools();
                        String tempPwd = sha.getDigestOfString(pwd.getBytes());
                        if(!tempPwd.equals(dbPwd)) {
                            flag = 2;
                            var18 = flag;
                            return var18;
                        } else {
                            Calendar cal = Calendar.getInstance();
                            cal.set(1900, 0, 1);
                            Date date = new Date(cal.getTimeInMillis());
                            Date lastSetDate = DateTools.formatDate(lastSet);
                            if(date.after(lastSetDate)) {
                                flag = 5;
                                var18 = flag;
                                return var18;
                            } else {
                                if((acountControl & 65536L) == 65536L) {
                                    flag = 4;
                                } else if(this.isOverdue(lastSet, pwdExpires)) {
                                    flag = 6;
                                }

                                rs.close();
                                ps.close();
                                this.conn.close();
                                return flag;
                            }
                        }
                    }
                } else {
                    var18 = flag;
                    return var18;
                }
            } catch (SQLException var30) {
                SsoLogger.error(var30);
                return flag;
            } finally {
                if(this.conn != null) {
                    try {
                        this.conn.close();
                    } catch (SQLException var29) {
                        SsoLogger.error(var29);
                    }
                }

            }
        }
    }

    private boolean isOverdue(String lastSet, String pwdLastSet) {
        long lastNowDays = DateTools.dateDiff(lastSet, DateTools.formatString(new Date(System.currentTimeMillis())));
        long pwdLastSetDays = DateTools.dateDiff(DateTools.formatString(new Date(System.currentTimeMillis())), pwdLastSet);
        long temp = 0L;
        long pdExpireDays = Long.valueOf((long)SsoClientUtils.ACCOUNT_POLICY).longValue();
        if(lastNowDays < pwdLastSetDays) {
            temp = lastNowDays;
            if(lastNowDays <= pdExpireDays) {
                temp = pdExpireDays - lastNowDays - 1L;
                this.setPsExDays((int)temp);
            }
        } else {
            temp = pwdLastSetDays;
            if(pwdLastSetDays <= pdExpireDays) {
                this.setPsExDays((int)pwdLastSetDays);
            }
        }

        return temp < 0L;
    }

    public int getDbPasswordAccountPolicy() {
        if(this.getConnection() == null) {
            return 180;
        } else {
            int temp = 180;

            try {
                String e = "SELECT id ,MaxPwdAge FROM TP_AccountPolicy";
                Statement stmt = this.conn.createStatement();

                ResultSet rs;
                for(rs = stmt.executeQuery(e); rs.next(); temp = rs.getInt("MaxPwdAge")) {
                    ;
                }

                rs.close();
                stmt.close();
            } catch (SQLException var13) {
                SsoLogger.error(var13);
            } finally {
                if(this.conn != null) {
                    try {
                        this.conn.close();
                    } catch (SQLException var12) {
                        SsoLogger.error(var12);
                    }
                }

            }

            return temp;
        }
    }

    public Map getOrgGroupPositinSysList(String userid) {
        if(this.getConnection() == null) {
            return this.map;
        } else {
            try {
                CallableStatement e = this.conn.prepareCall("{call GetOrgGroupPositinSystemCodeByID(?)}");
                e.setString(1, userid);
                ResultSet rs = e.executeQuery();
                String systemCode = "";
                String shortName = "";
                boolean isDisplay = false;
                boolean orderIndex = false;
                String url = "";

                String temp;
                int orderIndex1;
                for(temp = ""; rs.next(); this.map.put(systemCode, temp)) {
                    temp = "";
                    systemCode = rs.getString(1);
                    shortName = rs.getString(4);
                    isDisplay = rs.getBoolean(6);
                    orderIndex1 = rs.getInt(7);
                    url = rs.getString(5);
                    if(isDisplay) {
                        temp = temp + systemCode + "," + shortName + ",1," + orderIndex1 + "," + url;
                    } else {
                        temp = temp + systemCode + "," + shortName + ",0," + orderIndex1 + "," + url;
                    }
                }

                CallableStatement csUser = this.conn.prepareCall("{call GetUserSystemCodeByID(?)}");
                csUser.setString(1, userid);
                ResultSet rsUser = csUser.executeQuery();
                systemCode = "";
                shortName = "";
                isDisplay = false;
                orderIndex = false;
                url = "";
                boolean isUsed = false;

                while(rsUser.next()) {
                    temp = "";
                    systemCode = rsUser.getString(1);
                    shortName = rsUser.getString(4);
                    isDisplay = rsUser.getBoolean(6);
                    orderIndex1 = rsUser.getInt(7);
                    url = rsUser.getString(5);
                    isUsed = rsUser.getBoolean(8);
                    if(isDisplay) {
                        temp = temp + systemCode + "," + shortName + ",1," + orderIndex1 + "," + url;
                    } else {
                        temp = temp + systemCode + "," + shortName + ",0," + orderIndex1 + "," + url;
                    }

                    this.map.remove(systemCode);
                    if(isUsed) {
                        this.map.put(systemCode, temp);
                    }
                }

                rs.close();
                rsUser.close();
                csUser.close();
                e.close();
                this.conn.close();
            } catch (SQLException var21) {
                SsoLogger.error(var21);
            } finally {
                if(this.conn != null) {
                    try {
                        this.conn.close();
                    } catch (SQLException var20) {
                        SsoLogger.error(var20);
                    }
                }

            }

            return this.map;
        }
    }

    public void insertLog(int logType, String userid, String loginIp, String authNum, String authToke, String authMac, String message) {
        if(this.getConnection() != null) {
            try {
                GuidTool e = new GuidTool();
                String sqlStr = "INSERT INTO TA_Log (LogID, LogType, UserID, LogTime, SystemCode, LoginIP, AuthNum, AuthToke, AuthMAC, Message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = this.conn.prepareStatement(sqlStr);
                ps.setString(1, e.toString());
                ps.setInt(2, logType);
                ps.setString(3, userid);
                ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                ps.setString(5, SsoClientUtils.SYSTEM_CODE);
                ps.setString(6, loginIp);
                ps.setString(7, authNum);
                ps.setString(8, authToke);
                ps.setString(9, authMac);
                ps.setString(10, message);
                ps.execute();
                ps.close();
                this.conn.close();
            } catch (SQLException var19) {
                SsoLogger.error(var19);
            } finally {
                if(this.conn != null) {
                    try {
                        this.conn.close();
                    } catch (SQLException var18) {
                        SsoLogger.error(var18);
                    }
                }

            }

        }
    }

    public boolean isUserExist(String userName) {
        if(this.getConnection() == null) {
            return false;
        } else {
            boolean isExist = false;

            try {
                String e = "Select Count(UserID) From TF_User Where UserID=\'" + userName + "\'";
                PreparedStatement ps = this.conn.prepareStatement(e);
                ResultSet rs = ps.executeQuery();
                Integer count = null;
                if(rs.next()) {
                    count = Integer.valueOf(rs.getInt(1));
                    if(count.intValue() > 0) {
                        isExist = true;
                    }
                }

                rs.close();
                ps.close();
                this.conn.close();
            } catch (SQLException var15) {
                SsoLogger.error(var15);
            } finally {
                if(this.conn != null) {
                    try {
                        this.conn.close();
                    } catch (SQLException var14) {
                        SsoLogger.error(var14);
                    }
                }

            }

            return isExist;
        }
    }

    public String getUserSys(String userid) {
        StringBuffer temp = new StringBuffer("");
        ConnnectDbTools bean = new ConnnectDbTools();
        Map userMap = bean.getOrgGroupPositinSysList(userid);
        String[] tempArr = new String[userMap.size()];
        int i = -1;
        Iterator iter = userMap.entrySet().iterator();

        int var13;
        while(iter.hasNext()) {
            boolean index = false;
            ++i;
            Entry entry = (Entry)iter.next();
            tempArr[i] = (String)entry.getValue();

            for(int j = i; j > 0; --j) {
                var13 = Integer.parseInt(tempArr[j].split(",")[3]);
                int beforeIndex = Integer.parseInt(tempArr[j - 1].split(",")[3]);
                if(var13 >= beforeIndex) {
                    break;
                }

                String tempStr = tempArr[j - 1];
                tempArr[j - 1] = tempArr[j];
                tempArr[j] = tempStr;
            }
        }

        for(var13 = 0; var13 < tempArr.length; ++var13) {
            temp.append(tempArr[var13]);
            if(var13 + 1 < tempArr.length) {
                temp.append(";");
            }
        }

        var13 = temp.toString().indexOf(SsoClientUtils.SYSTEM_CODE);
        if("".equals(temp)) {
            return "0";
        } else if(var13 < 0) {
            return "-1";
        } else {
            return URLTool.encodeURL(temp.toString());
        }
    }

    public int getPsExDays() {
        return this.psExDays;
    }

    public void setPsExDays(int psExDays) {
        this.psExDays = psExDays;
    }
}