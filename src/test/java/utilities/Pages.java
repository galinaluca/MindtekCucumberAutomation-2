package utilities;

import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import pages.HRCreateEmployeePage;

public interface Pages {

    //All pages objects will be stored in Pages interface

    HRAppHomePage hrAppHomePage=new HRAppHomePage();
    HRAppLoginPage hrAppLoginPage=new HRAppLoginPage();
    HRCreateEmployeePage hrAppCreateEmployeePage=new HRCreateEmployeePage();

}
