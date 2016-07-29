package gear.yc.com.gearapplication.ui.mvp.travelnotes;

import org.junit.Before;
import org.junit.Test;

/**
 * GearApplication
 * Created by YichenZ on 2016/7/13 15:34.
 */
public class TravelNotesPresenterTest implements TravelNotesContract.View{
    TravelNotesPresenter travelNotesPresenter;

    @Before
    public void setUp() throws Exception {
        travelNotesPresenter=new TravelNotesPresenter(this,new TravelNotesActivity());
    }

    @Test
    public void refreshData() throws Exception {
        travelNotesPresenter.refreshData("",0,false);
    }

    @Test
    public void loadData() throws Exception {
        travelNotesPresenter.loadData("",0);
    }

    @Test
    public void loadData1() throws Exception {
        travelNotesPresenter.loadData("",0,0);
    }

    @Override
    public void changeListView() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void disDialog() {

    }
}