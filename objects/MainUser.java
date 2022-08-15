package sk.upjs.ics.android.beachcoachapp.objects;

public class MainUser {

    private MainUserData data;
    private static  MainUser instance;
    private MainUser(){}

    public static MainUser getInstance(){
        if (instance == null){
            instance = new MainUser();
        }
        return instance;
    }

    public void setData(MainUserData data){
        this.data = data;
    }

    public MainUserData getData() {
        return data;
    }
}
