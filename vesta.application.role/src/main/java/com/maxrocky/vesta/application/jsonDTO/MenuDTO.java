package com.maxrocky.vesta.application.jsonDTO;

/**
 * Created by chen on 2016/5/4.
 */
public class MenuDTO {
    /**
     *  1.问题清单 2.日常巡检 3.隐蔽验收 4.样板点评 5.材料验收 6.现场试验
     *  7.旁站 8.统计报表 9.内部预验 10. 工地开放 11.正式交房 12.一户一档
     *  13.整改单
     * */
    private String menuName;//用上面的数字代替

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
