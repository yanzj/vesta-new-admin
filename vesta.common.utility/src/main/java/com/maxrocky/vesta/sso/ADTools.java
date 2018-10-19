package com.maxrocky.vesta.sso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

class ADTools {
    public static final String MAXPWDAGE = "maxPwdAge";
    public static final String USERACCOUNTCONTROL = "userAccountControl";
    public static final String PWDLASTSET = "pwdLastSet";
    private DirContext ctx;
    private int psExDays = -1;
    private String searchBase;
    private Map hm = new HashMap();

    protected DirContext getContext() {
        return this.ctx;
    }

    public ADTools() throws NamingException {
        Hashtable ht = new Hashtable();
        ht.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        ht.put("java.naming.provider.url", "ldap://" + SsoClientUtils.AD_SERVER_IP);
        ht.put("java.naming.security.principal", SsoClientUtils.AD_ADMIN_USERID + SsoClientUtils.AD_SERVER_DOMAIN);
        ht.put("java.naming.security.credentials", SsoClientUtils.AD_ADMIN_USERPW);
        ht.put("java.naming.referral", "follow");
        ht.put("java.naming.ldap.attributes.binary", "objectGUID");
        ht.put("com.sun.jndi.ldap.connect.timeout", "3000");
        this.searchBase = SsoClientUtils.AD_DIRECTORYPATH;
        this.ctx = new InitialDirContext(ht);
        Attributes attrs = this.ctx.getAttributes(SsoClientUtils.AD_DIRECTORYPATH);
        long maxPwdAge = Long.parseLong((String)attrs.get("maxPwdAge").get());
        if(maxPwdAge < 0L) {
            maxPwdAge *= -1L;
        }

        this.getHm().put("maxPwdAge", Long.valueOf(maxPwdAge));
    }

    protected ADTools(LdapContext ctx) {
        this.ctx = ctx;
    }

    public boolean adTrueOrFalse(String userName, String passWord) {
        String url = new String("ldap://" + SsoClientUtils.AD_SERVER_IP + ":" + SsoClientUtils.AD_SERVER_PORT);
        String user = userName.indexOf(SsoClientUtils.AD_SERVER_DOMAIN) > 0?userName:userName + SsoClientUtils.AD_SERVER_DOMAIN;
        Hashtable env = new Hashtable();
        env.put("java.naming.security.authentication", "simple");
        env.put("java.naming.security.principal", user);
        env.put("java.naming.security.credentials", passWord);
        env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        env.put("java.naming.provider.url", url);

        try {
            InitialDirContext ctx = new InitialDirContext(env);
            ctx.close();
            return true;
        } catch (NamingException var8) {
            SsoLogger.error(var8);
            return false;
        }
    }

    private Attributes getUserByUsername(String username) throws NamingException {
        SearchControls sc = new SearchControls();
        sc.setSearchScope(2);
        String[] returnedAtts = new String[]{"userAccountControl", "pwdLastSet"};
        sc.setReturningAttributes(returnedAtts);
        NamingEnumeration ne = this.ctx.search(this.searchBase, "sAMAccountName=" + username, sc);
        Attributes attrs = null;
        if(ne.hasMore()) {
            SearchResult sr = (SearchResult)ne.next();
            attrs = sr.getAttributes();
        }

        ne.close();
        return attrs;
    }

    private void getData(Attributes attrs) throws Exception {
        if(attrs != null) {
            Attribute at = attrs.get("userAccountControl");
            if(at != null) {
                this.getHm().put("userAccountControl", (String)at.get());
            }

            at = attrs.get("pwdLastSet");
            if(at != null) {
                this.getHm().put("pwdLastSet", (String)at.get());
            }
        }

    }

    private int getUserStatus(String userName, String passWord) {
        byte flag = 3;
        long userAccountControl = Long.parseLong((String)this.getHm().get("userAccountControl"));
        String pwdLastSet = String.valueOf(this.getHm().get("pwdLastSet"));
        String maxPwdAge = String.valueOf(this.getHm().get("maxPwdAge"));
        if((userAccountControl & 2L) == 2L) {
            flag = 0;
            return flag;
        } else if("0".equals(pwdLastSet)) {
            flag = 5;
            return flag;
        } else {
            if((userAccountControl & 65536L) == 65536L) {
                flag = 4;
                this.setPsExDays(-1);
            } else if(this.isOverdue(pwdLastSet, maxPwdAge)) {
                flag = 6;
            }

            if(!this.adTrueOrFalse(userName, passWord)) {
                flag = 2;
                return flag;
            } else {
                return flag;
            }
        }
    }

    private boolean isOverdue(String pwdLastSet, String maxPwdAge) {
        long temp = Long.parseLong(pwdLastSet) + Long.parseLong(maxPwdAge);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1601, 0, 1, 0, 0);
        temp = temp / 10000L + calendar.getTime().getTime();
        calendar.setTimeInMillis(temp);
        Calendar calnow = Calendar.getInstance();
        long calnowlong = calnow.getTimeInMillis();
        long calendarlong = calendar.getTimeInMillis();
        calendarlong -= calnowlong;
        calendarlong /= 86400000L;
        int intDays = (int)calendarlong - 1;
        this.setPsExDays(intDays);
        return calendar.getTime().before(calnow.getTime());
    }

    private void close() throws NamingException {
        this.ctx.close();
        this.ctx = null;
    }

    public int getUserAdStatus(String username, String password) throws Exception {
        int flag = 0;
        Attributes attrs = this.getUserByUsername(username);
        if(attrs != null) {
            this.getData(attrs);
            flag = this.getUserStatus(username, password);
        }

        this.close();
        return flag;
    }

    public Map getHm() {
        return this.hm;
    }

    public void setHm(Map hm) {
        this.hm = hm;
    }

    public int getPsExDays() {
        return this.psExDays;
    }

    public void setPsExDays(int psExDays) {
        this.psExDays = psExDays;
    }
}
