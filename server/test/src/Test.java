import PutOut.MsgHeader;

/**
 * Created by richard on 2015/12/6.
 */
public class Test {
    public static void main(String[] args){
        PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingStub binding = null;
        try {
            binding = (PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvBindingStub)
                    new PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrv_ServiceLocator().getSB_WMS_WMS_ImportComEpuSimMatStorSrvPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            System.err.println("JAX-RPC ServiceException caught: " + jre);
        }
        binding.setTimeout(60000);
        PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvResponse value;
        PutOut.MsgHeader mh = new PutOut.MsgHeader("","","","",null,new java.math.BigDecimal(1),
                new java.math.BigDecimal(1),new java.math.BigDecimal(1),"","");
        PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem[] items = new PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvInputItem[1];
        PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest r = new PutOut.SB_WMS_WMS_ImportComEpuSimMatStorSrvRequest(mh ,
                items);
        try{
            value = binding.process(r);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
