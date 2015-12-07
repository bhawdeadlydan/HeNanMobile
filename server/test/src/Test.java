import PutIn.BOMS_Item;

import java.util.Calendar;

/**
 * Created by richard on 2015/12/6.
 */
public class Test {
    public static void main(String[] args){
        PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingStub binding = null;
        try {
            binding = (PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingStub)
                    new PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrv_ServiceLocator().getSB_WMS_WMS_ImportComEpuSimMatStorSrvPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            System.err.println("JAX-RPC ServiceException caught: " + jre);
        }
        binding.setTimeout(60000);
        PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvResponse value;
        PutIn.MsgHeader mh = new PutIn.MsgHeader("","","","", Calendar.getInstance(),new java.math.BigDecimal(1),
                new java.math.BigDecimal(1),new java.math.BigDecimal(1),"","");
        PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem[] items = new PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem[1];
        items[0] = new PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem();
        BOMS_Item[] bis = new BOMS_Item[1];
        items[0].setBOMS_Collection(bis);
        PutIn.WMS_DETAIL_Item[][][] wds = new PutIn.WMS_DETAIL_Item[1][1][1];
        items[0].setWMS_DETAILS_Collection(wds);
        PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest r = new PutIn.SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest(mh ,
                items);
        try{
            value = binding.process(r);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
