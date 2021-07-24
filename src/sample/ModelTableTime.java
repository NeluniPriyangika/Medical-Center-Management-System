package sample;

public class ModelTableTime {
    int sesid;
    String sesname,sesdate,sestime;


    public int getSesid() {
        return sesid;
    }

    public void setSesid(int sesid) {
        this.sesid = sesid;
    }

    public String getSesname() {
        return sesname;
    }

    public void setSesname(String sesname) {
        this.sesname = sesname;
    }

    public String getSesdate() {
        return sesdate;
    }

    public void setSesdate(String sesdate) {
        this.sesdate = sesdate;
    }

    public String getSestime() {
        return sestime;
    }

    public void setSestime(String sestime) {
        this.sestime = sestime;
    }

    public ModelTableTime(int sesid, String sesname, String sesdate, String sestime){
        this.sesid=sesid;
        this.sesname=sesname;
        this.sesdate=sesdate;
        this.sestime=sestime;
    }

}
