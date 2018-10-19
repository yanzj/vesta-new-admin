/**
 * Created by Magic on 2016/7/19.
 */
function CheckNull(str,name)
{
    if (JTrim(str) == "")
    {
        alert(name);
        return false;
    }else{
        return true;
    }
}
function JTrim(s)
{
    return s.replace(/(^\s*)|(\s*$)/g, "");
}