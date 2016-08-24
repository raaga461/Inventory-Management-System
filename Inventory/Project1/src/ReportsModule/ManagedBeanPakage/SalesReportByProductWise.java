package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.ProductWiseTableData;
import ReportsModule.BeanPakage.ReportByProductWise;

import ReportsModule.DBConnection.DBConnection;

import ReportsModule.PDFReports.SalesTheBigReportPDF;

import ReportsModule.Queries.Queries;

import com.mysql.jdbc.Statement;

import java.sql.ResultSet;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;


@ManagedBean(name = "repbyproduct",eager = true)
@ViewScoped
public class SalesReportByProductWise {
    private String amt;
   private  ReportByProductWise rpw=new ReportByProductWise();
   private List<ProductWiseTableData>tdata,tdata1;
   private ProductWiseTableData ptdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private List taxList=new ArrayList();
    private  List representativeList = new ArrayList();
    private List route  = new ArrayList();
    private List place = new ArrayList();
    private List group = new ArrayList();
    private List subgroup = new ArrayList();
    private List item = new ArrayList(); 
    
    public SalesReportByProductWise() {
        super();
        ProductWiseTableData ptdata=new ProductWiseTableData();
        List<ProductWiseTableData>tdata=new ArrayList<ProductWiseTableData>();
        rpw.setSalesType("All");
        rpw.setReType("All");
        rpw.setRoute("All");
        rpw.setItem("All");
        rpw.setGroup("All");
        rpw.setPlace("All");
        rpw.setSubGroup("All");
        taxData();
        addRepresents();
        addRoutes();
        addItems();
        AddItemGroup();
        RouteCombo();
        groupChanged();
        Date d = new Date();
        String date = df.format(d);

        try {
            rpw.setDate2(df.parse(date));
            rpw.setDate1( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
       GetData();
    }
    public void taxData() {
        taxList = Queries.taxData();
       /*  List taxData = new ArrayList();
        ResultSet rs = null;
        Statement st = null;
        st = DBConnection1.DBConnection.Conn_Statement();
        taxData.clear();
        taxData.add("All");
        try {
            String qry = "SELECT taxname FROM Tax";
            rs = st.executeQuery(qry);
            while (rs.next()) {
                taxData.add(rs.getString(1).toString());
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taxData; */
    }
    public void addRepresents() {
        representativeList=Queries.representData();
    /*     List rapresentData = new ArrayList();
        ResultSet rs = null;
        Statement st = null;
        st = DBConnection1.DBConnection.Conn_Statement();
        rapresentData.clear();
        rapresentData.add("All");
        try {
            String qry = "SELECT distinct(REPNAME)  FROM RouteMaster";
            rs = st.executeQuery(qry);
            while (rs.next()) {
               
                rapresentData.add(rs.getString(1));
                
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rapresentData; */
    }
    

    public void addRoutes() {
        route=Queries.addRoutes();
      /*   List routData = new ArrayList();
        ResultSet rs;
          Statement st;
          st = DBConnection1.DBConnection.Conn_Statement();
        try {
            String qry = "SELECT distinct(ROUNO)  FROM RouteMaster";
            rs = st.executeQuery(qry);
           routData.clear(); 
           routData.add("All");
            while (rs.next()) {
           routData.add(rs.getString(1));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    return routData; */
    }
    public void addItems() {
        item=Queries.addItems();
         /*   List items = new ArrayList();
           ResultSet rs;
             Statement st;
             st = DBConnection1.DBConnection.Conn_Statement();
           try {
             
               String qry = "SELECT distinct(itemname)  FROM ItemMaster";
               rs=st.executeQuery(qry);
               items.clear();
              items.add("All");
               while (rs.next()) {      
                   String  i = rs.getString(1);
                   if(i!=null)
                   {
                   items.add(i.trim());
                   }
               }
               st.close();

           } catch (Exception e) {
               e.printStackTrace();
           }
    return items; */
       }
    public void AddItemGroup() {
        group=Queries.AddItemGroup();
          /*   List itemGroup = new ArrayList();
            ResultSet rs;
              Statement st;
              st = DBConnection1.DBConnection.Conn_Statement();
            try {
               
                String qry = "SELECT distinct(CATEGORY) FROM ItemMaster";
                itemGroup.clear();
                itemGroup.add("All");
                rs=st.executeQuery(qry);
                while (rs.next()) {
                   
               itemGroup.add(rs.getString(1));
                    
                }
                rs.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemGroup; */
        }
    public void RouteCombo()
        {     
        place=Queries.RouteCombo(rpw);
  /*       List places=new ArrayList();
            ResultSet rs;
              Statement st;
              st = DBConnection1.DBConnection.Conn_Statement();
                String qry = "";
            if(rpw.getRoute()!=null && rpw.getReType()!=null){
            try {
                String sel =rpw.getRoute();
                String rsel = rpw.getReType();
                places.clear();
               places.add("All");   
               if(sel.equalsIgnoreCase("All"))
               {  
                    if(rsel.equalsIgnoreCase("All"))
                    {            
                    rsel = "%";
                    }
                    
          qry = "SELECT AREANAME  FROM RouteMaster  WHERE  REPNAME like  '%"+rsel+"%'   ORDER BY ROUNO";
              }
               else{
                  if(rsel.equalsIgnoreCase("All"))
                  {
                    
                    rsel = "%";
                    }
                 qry = "SELECT AREANAME  FROM RouteMaster WHERE ROUNO = '"+sel+"' and REPNAME like  '%"+rsel+"%'   ORDER BY ROUNO";
               }
                System.out.println(""+qry);
                 rs = st.executeQuery(qry);
                 while(rs.next())
                 {
                 places.add(rs.getString(1));
                 }         
            rs.close();         
            } catch (Exception e) {
                e.printStackTrace();
            }   
            
        }
        return places; */
        }
    public void GetData() {
        tdata=Queries.GetData(rpw);
           /*  List<ProductWiseTableData>tdata1=new ArrayList<ProductWiseTableData>();
             ResultSet rs;
              Statement st;
              st = DBConnection1.DBConnection.Conn_Statement();
            String qry= " ";
            String d1, d2;
            d1 = df.format(rpw.getDate1());
            d2 = df.format(rpw.getDate2());
            if(rpw.getSalesType()!=null&& rpw.getReType()!=null&& rpw.getRoute()!=null&& rpw.getPlace()!=null&& rpw.getItem()!=null&& rpw.getGroup()!=null && rpw.getSubGroup()!=null ){
           Double  amount = 0.0,qty = 0.0;
            try {
            
          String stype= "", repre= "", rotid= "",city= "",item= "",grop= "",subgrop= "";
          
          stype =  rpw.getSalesType();
          if(stype.equalsIgnoreCase("All"))
          {
          stype = "%";
          }  
          repre =  rpw.getReType();
           if(repre.equalsIgnoreCase("All"))
          {
          repre = "%";
          }   
          city = rpw.getPlace();
           if(city.equalsIgnoreCase("All"))
          {
          city = "%";
          }
          
          
           item =rpw.getItem();
            if(item.equalsIgnoreCase("All"))
          {
          item = "%";
          }   
           grop = rpw.getGroup();
            if(grop.equalsIgnoreCase("All"))
          {
          grop = "%";
          }    
          subgrop =  rpw.getSubGroup();
           if(subgrop.equalsIgnoreCase("All"))
          {
          subgrop = "%";
          }
      rotid = rpw.getRoute();
           if(rotid.equalsIgnoreCase("All"))
          {
           
          qry = "SELECT   acname,city,R_rep,prodname,subgrp,icat,invvalue,SUM(quantity)   FROM Vat_Sales  WHERE taxtype  like  '%" + stype + "%' and  invdate between '"+d2+"' and '"+d1+"' and city like '%" + city + "%' and R_rep like '%" + repre + "%' and icat like '%" + grop + "%'      and  prodname like '%" + item + "%' and  subgrp like '%" + subgrop + "%' GROUP BY   acname,city,R_rep, prodname,subgrp,icat,invvalue order by  R_rep";
               
          }else
           
           {
        qry = "SELECT   acname,city,R_rep, prodname,subgrp,icat,invvalue,SUM(quantity)   FROM Vat_Sales  WHERE taxtype  like  '%" + stype + "%' and  invdate between '" +d2 + "' AND '"+d1+"' and city like '%" + city + "%'  and  R_rep like '%" + repre + "%' and  icat like '%" + grop + "%'      and  R_id= '"+rotid+"' and prodname like '%" + item + "%' and  subgrp like '%" + subgrop + "%'  GROUP BY   acname,city,R_rep, prodname,subgrp,icat,invvalue    order by  R_rep";
           
           }
                System.out.println("Get Data Query----------------------->" + qry);

                rs = st.executeQuery(qry);
                while (rs.next()) {
                    ProductWiseTableData ptdata=new ProductWiseTableData();
                    String net = rs.getString(7);
                     String qtt = rs.getString(8);
                    amount = Double.parseDouble(net) + amount;
                      qty = Double.parseDouble(qtt) +qty;
                    ptdata.setPartyName(rs.getString(1));
                    ptdata.setCity(rs.getString(2));
                    ptdata.setRepresents(rs.getString(3));
                    ptdata.setItemName(rs.getString(4));
                    ptdata.setItemSubgroup(rs.getString(5));
                    ptdata.setItemGroup(rs.getString(6));
                    ptdata.setQuantity(qtt);
                    ptdata.setInvAmount(net);
                    tdata1.add(ptdata);
                }
               
    rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
            return tdata1; */
        }
    public void print(){
        String stype= "", repre= "", rotid= "",city= "",item= "",grop= "",subgrop= "";
             
             stype =  rpw.getSalesType();
             if(stype.equalsIgnoreCase("All"))
             {
             stype = "%";
             }
             
             repre =  rpw.getReType();
              if(repre.equalsIgnoreCase("All"))
             {
             repre = "%";
             }          
             city = rpw.getPlace();
              if(city.equalsIgnoreCase("All"))
             {
             city = "%";
             }       
              item = rpw.getItem();
               if(item.equalsIgnoreCase("All"))
             {
             item = "%";
             }
             
             
              grop = rpw.getGroup();
               if(grop.equalsIgnoreCase("All"))
             {
             grop = "%";
             }
             
             
             subgrop =  rpw.getSubGroup();
              if(subgrop.equalsIgnoreCase("All"))
             {
             subgrop = "%";
             }
             

         rotid = rpw.getRoute();
              if(rotid.equalsIgnoreCase("All"))
             {
                 rotid = "%";
             } 
                SalesTheBigReportPDF ob = new SalesTheBigReportPDF();
                  ob.execute(stype, repre, rotid,city,item,grop,subgrop,df.format(rpw.getDate2()), df.format(rpw.getDate1()));
    }
    
    public void typeChanged() {  
     //  tdata.clear();
         GetData();
       }  
    public void routeChanged(){
        place.clear();
    RouteCombo();
    GetData();
    }
    public void repChanged(){
      //  place.clear();
     //   tdata.clear();
        RouteCombo();
        GetData();
    }
    public void placeChanged(){
        tdata.clear();
        GetData();
    }
    public void itemChanged(){
        tdata.clear();
        GetData();
    }
    public void groupChanged(){
        ResultSet rs;
         Statement st;
         st = DBConnection.Conn_Statement();
        String qry = "";
              if(rpw.getGroup()!=null){
              try {      
                  String sel = rpw.getGroup();
           subgroup.clear();
                  subgroup.add("ALL");
                  
                  if(sel.equalsIgnoreCase("All"))
                  {
                  
                 qry = "SELECT distinct(`subgrp`)  FROM `ItemMaster`";
                  }
                  else{
                    System.out.println("iam from SUBGroup********************************");
                   qry = "SELECT distinct(`subgrp`)  FROM `ItemMaster` WHERE CATEGORY like '%"+sel+"%'";
                  }
                   rs = st.executeQuery(qry);
                   while(rs.next())
                   {    System.out.println("Adding0*******************************000*"+subgroup);
                  subgroup.add(rs.getString(1));
                       
                   }
              
              rs.close();
                       
                  sel = rpw.getGroup();
                          item.clear();                
                  item.add("All");
                          
                          if(sel.equalsIgnoreCase("All"))
                          {
                          
                         qry = "SELECT distinct(`itemname`)  FROM `ItemMaster`";
                          }
                          else{
                           qry = "SELECT distinct(`itemname`)  FROM `ItemMaster` WHERE CATEGORY like  '%"+sel+"%'";
                          }
                           rs = st.executeQuery(qry);
                           while(rs.next())
                           {
                           item.add(rs.getString(1));
                           }
                      
                      rs.close();
                 
                  
              } catch (Exception e) {
                  e.printStackTrace();
              }
            
              
         
//          tdata.clear();
            GetData();
          }
    }
    public void subgroupChanged(){
        String qry = "";
        ResultSet rs;
         Statement st;
         st = DBConnection.Conn_Statement();
                if(rpw.getSubGroup()!=null && rpw.getGroup()!=null){
                try {
                    String ic = rpw.getGroup();
                    String sg = rpw.getSubGroup();
                    item.clear();
            item.add("All");
                    
                    if(ic.equalsIgnoreCase("All") && sg.equalsIgnoreCase("All"))
                    {
                    
                   qry = "SELECT distinct(`itemname`)  FROM `ItemMaster`";
                    }
                    else{
                     qry = "SELECT distinct(`itemname`)  FROM `ItemMaster` WHERE CATEGORY like '%"+ic+"%' and `subgrp` like '%"+sg+"%' ORDER BY `itemname`";
                    }
                     rs = st.executeQuery(qry);
                     while(rs.next())
                     {
                  item.add(rs.getString(1));
                     }
                
                rs.close();
                
                
                
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
              
                
                
               tdata.clear();
               GetData();
            }        // TODO add your handling 
    }
    public void dateSelect(SelectEvent event) {
        rpw.setDate2((Date)event.getObject());

    }
    public void todateSelect(SelectEvent event) {
        rpw.setDate1((Date)event.getObject());

    }
    public void refresh(){
        rpw.setDate2(null);
        rpw.setSalesType("All");
        rpw.setReType("All");
        rpw.setRoute("All");
        rpw.setItem("All");
        rpw.setGroup("All");
        rpw.setPlace("All");
        rpw.setSubGroup("All");
        amt = "0.0";
        tdata.clear(); 
    }
    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getAmt() {
        return amt;
    }

    public void setRpw(ReportByProductWise rpw) {
        this.rpw = rpw;
    }

    public ReportByProductWise getRpw() {
        return rpw;
    }

    public void setTdata(List<ProductWiseTableData> tdata) {
        this.tdata = tdata;
    }

    public List<ProductWiseTableData> getTdata() {
        return tdata;
    }

    public void setTaxList(List taxList) {
        this.taxList = taxList;
    }

    public List getTaxList() {
        return taxList;
    }

    public void setRepresentativeList(List representativeList) {
        this.representativeList = representativeList;
    }

    public List getRepresentativeList() {
        return representativeList;
    }

    public void setRoute(List route) {
        this.route = route;
    }

    public List getRoute() {
        return route;
    }

    public void setPlace(List place) {
        this.place = place;
    }

    public List getPlace() {
        return place;
    }

    public void setGroup(List group) {
        this.group = group;
    }

    public List getGroup() {
        return group;
    }

    public void setSubgroup(List subgroup) {
        this.subgroup = subgroup;
    }

    public List getSubgroup() {
        return subgroup;
    }

    public void setItem(List item) {
        this.item = item;
    }

    public List getItem() {
        return item;
    }

    public void setPtdata(ProductWiseTableData ptdata) {
        this.ptdata = ptdata;
    }

    public ProductWiseTableData getPtdata() {
        return ptdata;
    }
}

