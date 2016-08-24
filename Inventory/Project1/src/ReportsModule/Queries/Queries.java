package ReportsModule.Queries;


import ReportsModule.BeanPakage.AccountGroupTableBean;
import ReportsModule.BeanPakage.CFormByCompanyBean;
import ReportsModule.BeanPakage.CFormByCompanyTableBean;
import ReportsModule.BeanPakage.CFormByRepBean;
import ReportsModule.BeanPakage.CFormByRepTableBean;
import ReportsModule.BeanPakage.CFormOutstandingSummaryBean;
import ReportsModule.BeanPakage.CFormOutstandingSummaryTableBean;
import ReportsModule.BeanPakage.CFormQuartersItemTableBean;
import ReportsModule.BeanPakage.CFormQuartersSubTableBean;
import ReportsModule.BeanPakage.CFormStatusByQuartersBean;
import ReportsModule.BeanPakage.CFormStatusByQuartersTableBean;
import ReportsModule.BeanPakage.CFormSubmitDatesBean;
import ReportsModule.BeanPakage.CFormSubmitDatesTableBean;
import ReportsModule.BeanPakage.CformStatusByDateBean;
import ReportsModule.BeanPakage.CformStatusByDateBillTableBean;
import ReportsModule.BeanPakage.CformStatusByDateItemBean;
import ReportsModule.BeanPakage.CformStatusByDateTableBean;
import ReportsModule.BeanPakage.ChequeReturnsBean;
import ReportsModule.BeanPakage.ChequeReturnsTableBean;
import ReportsModule.BeanPakage.CurrentBalanceBean;
import ReportsModule.BeanPakage.CurrentBalanceTableBean;
import ReportsModule.BeanPakage.LedgerBean;
import ReportsModule.BeanPakage.LedgerReportBean;
import ReportsModule.BeanPakage.LedgerReportTableData;
import ReportsModule.BeanPakage.LedgerTableData;
import ReportsModule.BeanPakage.PartyOutstandingBean;
import ReportsModule.BeanPakage.PartyOutstandingTableBean;
import ReportsModule.BeanPakage.ProductWiseTableData;
import ReportsModule.BeanPakage.PurchaseItemBean;
import ReportsModule.BeanPakage.PurchaseItemStockBean;
import ReportsModule.BeanPakage.PurchaseItemStockTableBean;
import ReportsModule.BeanPakage.PurchaseItemTableBean;
import ReportsModule.BeanPakage.PurchaseQuotationBean;
import ReportsModule.BeanPakage.PurchaseQuotationTable;
import ReportsModule.BeanPakage.PurchaseReportBean;
import ReportsModule.BeanPakage.PurchaseReportTableBean;
import ReportsModule.BeanPakage.PurchaseStockBean;
import ReportsModule.BeanPakage.PurchaseStockTableBean;
import ReportsModule.BeanPakage.ReportByProductWise;
import ReportsModule.BeanPakage.RouteDataBean;
import ReportsModule.BeanPakage.RouteDataTableBean;
import ReportsModule.BeanPakage.SalesChartbyRepwiseBean;
import ReportsModule.BeanPakage.SalesCurrentStockBean;
import ReportsModule.BeanPakage.SalesCurrentStockTableBean;
import ReportsModule.BeanPakage.SalesDateBean;
import ReportsModule.BeanPakage.SalesDateTableData;
import ReportsModule.BeanPakage.SalesInvoiceBean;
import ReportsModule.BeanPakage.SalesInvoiceTableBean;
import ReportsModule.BeanPakage.SalesItemChartBean;
import ReportsModule.BeanPakage.SalesItemChartSubTableBean;
import ReportsModule.BeanPakage.SalesItemChartTableBean;
import ReportsModule.BeanPakage.SalesItemDataBean;
import ReportsModule.BeanPakage.SalesItemTableBean;
import ReportsModule.BeanPakage.SalesQuotationTableBean;
import ReportsModule.BeanPakage.SalesQuotationsBean;
import ReportsModule.BeanPakage.SalesRepWiseBean;
import ReportsModule.BeanPakage.SalesStockBean;
import ReportsModule.BeanPakage.SalesStockTableBean;
import ReportsModule.BeanPakage.TaxAccountReportTableBean;
import ReportsModule.BeanPakage.VoucherReportBean;
import ReportsModule.BeanPakage.VoucherReportTableBean;
import ReportsModule.BeanPakage.YearlySalesBean;
import ReportsModule.BeanPakage.YearlySalesTableBean;

import ReportsModule.DBConnection.DBConnection;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.PieChartModel;


public class Queries {
    static DecimalFormat dfr = new DecimalFormat("#.##");
    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Queries() {
        super();
    }
    
    
    public static List<PurchaseItemStockTableBean> pist( PurchaseItemStockBean sd) {
            List<PurchaseItemStockTableBean> tdata = new ArrayList<PurchaseItemStockTableBean>();
            try {

                tdata = DBConnection.pist(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
    
    public static List<SalesCurrentStockTableBean> item( SalesCurrentStockBean sd) {
            List<SalesCurrentStockTableBean> tdata = new ArrayList<SalesCurrentStockTableBean>();
            try {

                tdata = DBConnection.item(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
    
    public static List<SalesStockTableBean> salesStock( SalesStockBean sd) {
            List<SalesStockTableBean> tdata = new ArrayList<SalesStockTableBean>();
            try {

                tdata = DBConnection.salesStock(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
    
    public static List<SalesQuotationTableBean> quotData( SalesQuotationsBean sd) {
            List<SalesQuotationTableBean> tdata = new ArrayList<SalesQuotationTableBean>();
            try {

                tdata = DBConnection.quotdata(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
    
    
    public static List<PurchaseStockTableBean> purStock( PurchaseStockBean sd) {
            List<PurchaseStockTableBean> tdata = new ArrayList<PurchaseStockTableBean>();
            try {

                tdata = DBConnection.purStock(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
    
    
    public static List routeCombo1(PartyOutstandingBean rpw){
            List place=new ArrayList();
            try{
                place=DBConnection.routeCombo1(rpw);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return place;
        }
    
    
    
    public static List Getplace(){
            List taxList = new ArrayList();
            try{
            taxList=DBConnection.Getplace();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return taxList;
        }
    
    
    public static List<CFormByCompanyTableBean> compData( CFormByCompanyBean sd) {
           List<CFormByCompanyTableBean> tdata = new ArrayList<CFormByCompanyTableBean>();
           try {

               tdata = DBConnection.compData(sd);

           } catch (Exception e) {
               e.printStackTrace();
           }
           return tdata;
       }
       
        
        public static List<PurchaseQuotationTable> purData( PurchaseQuotationBean sd) {
            List<PurchaseQuotationTable> tdata = new ArrayList<PurchaseQuotationTable>();
            try {

                tdata = DBConnection.purdata(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
        

    public static List<SalesDateTableData> tableData( SalesDateBean sd) {
        List<SalesDateTableData> tdata = new ArrayList<SalesDateTableData>();
        try {

            tdata = DBConnection.salesdata(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    public static List<LedgerTableData> ledTableData1( LedgerBean sd) {
            List<LedgerTableData> tdata = new ArrayList<LedgerTableData>();
            try {

                tdata = DBConnection.ledData(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
        
        public static List<LedgerReportTableData> tableData1( LedgerReportBean sd) {
            List<LedgerReportTableData> tdata = new ArrayList<LedgerReportTableData>();
            try {

                tdata = DBConnection.ledreport(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
        
    
    public static List representData1(){
            List taxList = new ArrayList();
            try{
            taxList=DBConnection.representData1();
               // System.out.println("List is"+taxList);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return taxList;
        }
    
    public static List<PartyOutstandingTableBean> pData(PartyOutstandingBean rpw) {
            List<PartyOutstandingTableBean>tdata=new ArrayList<PartyOutstandingTableBean>();
            try{
                tdata=DBConnection.pData(rpw);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return tdata;
        }
    
    public static List<CFormByRepTableBean> crepData( CFormByRepBean sd) {
            List<CFormByRepTableBean> tdata = new ArrayList<CFormByRepTableBean>();
            try {

                tdata = DBConnection.crepData(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
    
    public static List<CFormOutstandingSummaryTableBean> cosData( CFormOutstandingSummaryBean sd) {
            List<CFormOutstandingSummaryTableBean> tdata = new ArrayList<CFormOutstandingSummaryTableBean>();
            try {

                tdata = DBConnection.cosData(sd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tdata;
        }
    
    public static List<CurrentBalanceTableBean> currentBalanceData( CurrentBalanceBean sd) {
        List<CurrentBalanceTableBean> tdata = new ArrayList<CurrentBalanceTableBean>();
        try {

            tdata = DBConnection.currentBalanceData(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<TaxAccountReportTableBean> taxDetailsData( ) {
        List<TaxAccountReportTableBean> tdata = new ArrayList<TaxAccountReportTableBean>();
        try {

            tdata = DBConnection.taxDetailsData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<AccountGroupTableBean> accDetailsData( ) {
        List<AccountGroupTableBean> tdata = new ArrayList<AccountGroupTableBean>();
        try {

            tdata = DBConnection.accDetailsData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<PurchaseItemTableBean> purchaseItemData( PurchaseItemBean sd) {
        List<PurchaseItemTableBean> tdata = new ArrayList<PurchaseItemTableBean>();
        try {

            tdata = DBConnection.purchaseItemData(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    public static List types(){
            List taxList = new ArrayList();
            try{
            taxList=DBConnection.types();
               // System.out.println("List is"+taxList);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return taxList;
        }
        public static List types1(){
            List taxList = new ArrayList();
            try{
            taxList=DBConnection.types1();
               // System.out.println("List is"+taxList);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return taxList;
        }
    public static List<SalesItemTableBean> salesItemData( SalesItemDataBean sd) {
        List<SalesItemTableBean> tdata = new ArrayList<SalesItemTableBean>();
        try {

            tdata = DBConnection.salesItemData(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<ChequeReturnsTableBean> chequeRetData( ChequeReturnsBean sd) {
        List<ChequeReturnsTableBean> tdata = new ArrayList<ChequeReturnsTableBean>();
        try {

            tdata = DBConnection.chequeRetData(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<RouteDataTableBean> routeData( RouteDataBean sd) {
        List<RouteDataTableBean> tdata = new ArrayList<RouteDataTableBean>();
        try {

            tdata = DBConnection.routeData(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<PurchaseReportTableBean> purchaseData( PurchaseReportBean sd) {
        List<PurchaseReportTableBean> tdata = new ArrayList<PurchaseReportTableBean>();
        try {

            tdata = DBConnection.purchaseData(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<SalesDateTableData> repwiseTableData(SalesRepWiseBean srb) {
        List<SalesDateTableData> tdata = new ArrayList<SalesDateTableData>();
        try {

            tdata = DBConnection.repWiseTableData(srb);

        } catch (Exception e) {
            e.printStackTrace();
        }   
       return tdata; 
    }
    public static List<CformStatusByDateTableBean> cformTableData(CformStatusByDateBean cbd){
        List<CformStatusByDateTableBean>  tdata=new ArrayList<CformStatusByDateTableBean>();
        try {
            tdata = DBConnection.cformTableData(cbd);

        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    }
    public static List<VoucherReportTableBean> voucherTableData(VoucherReportBean vrb){
        List<VoucherReportTableBean>  tdata=new ArrayList<VoucherReportTableBean>();
        try {
            tdata = DBConnection.voucherTableData(vrb);

        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    }
    public static List<CformStatusByDateItemBean> itemTableData(CformStatusByDateBillTableBean selectedcib){
        List<CformStatusByDateItemBean>  tdata=new ArrayList<CformStatusByDateItemBean>();
        try {
            tdata = DBConnection.itemData(selectedcib);

        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    }
    public static List<CformStatusByDateBillTableBean> billData(CformStatusByDateBean cbd,CformStatusByDateTableBean selectedCbt){
        List<CformStatusByDateBillTableBean>  tdata=new ArrayList<CformStatusByDateBillTableBean>();
        try {
            tdata = DBConnection.billData(cbd,selectedCbt);

        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    } 
    public static List<CFormQuartersSubTableBean> subData(CFormStatusByQuartersBean cbd,CFormStatusByQuartersTableBean selectedCbt,String s){
        List<CFormQuartersSubTableBean>  tdata=new ArrayList<CFormQuartersSubTableBean>();
        try {
            tdata = DBConnection.subData(cbd,selectedCbt,s);

        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    } 
    public static List<CFormQuartersItemTableBean> quarterItemData(CFormQuartersSubTableBean cqst){
        List<CFormQuartersItemTableBean>  tdata=new ArrayList<CFormQuartersItemTableBean>();
        try {
            tdata = DBConnection.quarterItemData(cqst);

        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    } 
    public static List<CFormSubmitDatesTableBean> cformSubmitData(CFormSubmitDatesBean cbd){
        List<CFormSubmitDatesTableBean>  tdata=new ArrayList<CFormSubmitDatesTableBean>();
        try {
            tdata = DBConnection.cformSubmitData(cbd);

        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    } 
    
    
    
    public static PieChartModel createPieModel(SalesChartbyRepwiseBean sd ) {
        PieChartModel pieModel = new PieChartModel();
        try {

            pieModel= DBConnection.createPieModel(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        return pieModel;
    }
    public static PieChartModel DataCharta(SalesItemChartBean sd ) {
        PieChartModel pieModel = new PieChartModel();
        try {

            pieModel= DBConnection.DataCharta(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        return pieModel;
    }
    public static PieChartModel dataChartb(SalesItemChartBean sd,String icat){
        PieChartModel pieModel = new PieChartModel();
        try {

            pieModel= DBConnection.dataChartb(sd,icat);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pieModel;
    }
    
    public static PieChartModel dataChartc(SalesItemChartBean sd,String icat){
        PieChartModel pieModel = new PieChartModel();
        try {

            pieModel= DBConnection.dataChartc(sd, icat);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pieModel;
    }
    
    public static List<SalesDateTableData> charttableData( SalesChartbyRepwiseBean sd) {
        List<SalesDateTableData> tdata = new ArrayList<SalesDateTableData>();
        try {

            tdata = DBConnection.chartTableData(sd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<SalesItemChartSubTableBean> subTableData( SalesItemChartBean sd,String s) {
        List<SalesItemChartSubTableBean> tdata = new ArrayList<SalesItemChartSubTableBean>();
        try {

            tdata = DBConnection.subTableData(sd,s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    public static List<CFormStatusByQuartersTableBean> quarteraData( String s) {
        List<CFormStatusByQuartersTableBean> tdata = new ArrayList<CFormStatusByQuartersTableBean>();
        try {

            tdata = DBConnection.quarteraData(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    public static List<CFormStatusByQuartersTableBean> quarterNameData( CFormStatusByQuartersBean csqb,String s) {
        List<CFormStatusByQuartersTableBean> tdata = new ArrayList<CFormStatusByQuartersTableBean>();
        try {

            tdata = DBConnection.quarterNameData(csqb,s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    
    
    
    
    
    
    
    
    public static List< YearlySalesTableBean> yearlyData( YearlySalesBean ysb) {
        List<YearlySalesTableBean> tdata = new ArrayList<YearlySalesTableBean>();
        try {
            tdata = DBConnection.yearlyData(ysb);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List taxData(){
        List taxList = new ArrayList();
        try{
        taxList=DBConnection.taxdata();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return taxList;
    }
    public static List yearData(){
        List yList = new ArrayList();
        try{
        yList=DBConnection.yearData();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return yList;
    }
    public static List icatData(){
        List icatList = new ArrayList();
        try{
        icatList=DBConnection.icatData();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return icatList;
    }
    public static List representData(){
        List repList=new ArrayList();
        try{
        repList=DBConnection.addRepresents();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return repList; 
    }
    
    
    public static List itemData(){
        List itemList=new ArrayList();
        try{
        itemList=DBConnection.itemData();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return itemList; 
    }
    public static List routeListData(SalesItemChartBean sicb){
        List itemList=new ArrayList();
        try{
        itemList=DBConnection.routeListData(sicb);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return itemList; 
    }
    public static List itemgroupListData(){
        List itemList=new ArrayList();
        try{
        itemList=DBConnection.itemgroupListData();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return itemList; 
    }
  
    public static List partyData(){
        List partyList=new ArrayList();
        try{
        partyList=DBConnection. addparties();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return partyList; 
    }
   
    public static List addRoutes() {
        List routData=new ArrayList();
        try{
            routData=DBConnection.addRoutes();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return routData;
    }

    
    public static List addRoutes1() {
        List routData=new ArrayList();
        try{
            routData=DBConnection.addRoutes1();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return routData;
    }
    
    public static List addItems() {
        List items=new ArrayList();
        try{
            items=DBConnection.addItems();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return items;
    }
    public static List AddItemGroup() {
        List itemGroup=new ArrayList();
        try{
            itemGroup=DBConnection.AddItemGroup();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return itemGroup;
    }
    public static List RouteCombo(ReportByProductWise rpw){
        List place=new ArrayList();
        try{
            place=DBConnection.RouteCombo(rpw);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return place;
    }
    public static List SalesInvoiceRouteCombo(SalesInvoiceBean rpw){
      //  List place=new ArrayList();
        List place=new ArrayList();
        try{
            place=DBConnection.SalesInvoiceRouteCombo(rpw);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return place;
    }
    public static void ivoiceCurrPdfData( List<SalesInvoiceTableBean>tdata){
    DBConnection.ivoiceCurrPdfData(tdata);
    
    }
    public static List< SalesInvoiceTableBean> salesInvoiceData( SalesInvoiceBean sib) {
        List<SalesInvoiceTableBean> tdata = new ArrayList<SalesInvoiceTableBean>();
        try {
            tdata = DBConnection.InvoiceData(sib);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    public static List<SalesItemChartTableBean> saleschartData(SalesItemChartBean rpw) {
        List<SalesItemChartTableBean>tdata=new ArrayList<SalesItemChartTableBean>();
        try{
            tdata=DBConnection.saleschartData(rpw);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return tdata;
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static List<ProductWiseTableData> GetData(ReportByProductWise rpw) {
        List<ProductWiseTableData>tdata=new ArrayList<ProductWiseTableData>();
        try{
            tdata=DBConnection.GetData(rpw);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return tdata;
    }
    
}
  

