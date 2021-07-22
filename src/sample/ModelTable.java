package sample;

public class ModelTable {
    int pno;
    String pname,pid,dname,pphone,appdate,apptime,covidnote;

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getAppdate() {
        return appdate;
    }

    public void setAppdate(String appdate) {
        this.appdate = appdate;
    }

    public String getApptime() {
        return apptime;
    }

    public void setApptime(String apptime) {
        this.apptime = apptime;
    }

    public String getCovidnote() {
        return covidnote;
    }

    public void setCovidnote(String covidnote) {
        this.covidnote = covidnote;
    }

    public ModelTable(int pno,String pname, String pid, String dname, String pphone, String appdate,
                      String apptime, String covidnote){
        this.pno=pno;
        this.pname=pname;
        this.pid=pid;
        this.dname=dname;
        this.pphone=pphone;
        this.appdate=appdate;
        this.apptime=apptime;
        this.covidnote=covidnote;



    }
}
