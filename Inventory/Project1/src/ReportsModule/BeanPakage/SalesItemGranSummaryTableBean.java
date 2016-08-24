package ReportsModule.BeanPakage;

import java.util.HashMap;
import java.util.Map;

public class SalesItemGranSummaryTableBean {
   private Map<String,String>data=new HashMap<String,String>();

    
    public SalesItemGranSummaryTableBean() {
        super();
    }


    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }
}
