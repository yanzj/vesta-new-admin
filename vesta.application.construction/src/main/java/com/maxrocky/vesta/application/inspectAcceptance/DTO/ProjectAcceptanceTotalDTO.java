package com.maxrocky.vesta.application.inspectAcceptance.DTO;

/**
 * Created by jiazefeng on 2016/10/21.
 */
public class ProjectAcceptanceTotalDTO {
    private int hasBeenGetOnTotal;//已进行
    private int qualifiedToatl;//合格
    private int unqualifiedToatl;//不合格
    private int onePassToatl;//一次通过

    public int getHasBeenGetOnTotal() {
        return hasBeenGetOnTotal;
    }

    public void setHasBeenGetOnTotal(int hasBeenGetOnTotal) {
        this.hasBeenGetOnTotal = hasBeenGetOnTotal;
    }

    public int getQualifiedToatl() {
        return qualifiedToatl;
    }

    public void setQualifiedToatl(int qualifiedToatl) {
        this.qualifiedToatl = qualifiedToatl;
    }

    public int getUnqualifiedToatl() {
        return unqualifiedToatl;
    }

    public void setUnqualifiedToatl(int unqualifiedToatl) {
        this.unqualifiedToatl = unqualifiedToatl;
    }

    public int getOnePassToatl() {
        return onePassToatl;
    }

    public void setOnePassToatl(int onePassToatl) {
        this.onePassToatl = onePassToatl;
    }
}
