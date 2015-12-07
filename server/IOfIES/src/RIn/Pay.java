package RIn;

import javax.xml.rpc.ServiceException;

/**
 * Created by richard on 2015/12/7.
 */
public class Pay {
    public static void process(){
        SB_IES_IES_ImportComEpuSimMatPaidSrvBindingStub binding = null;
        try{
            binding = (SB_IES_IES_ImportComEpuSimMatPaidSrvBindingStub)new SB_IES_IES_ImportComEpuSimMatPaidSrv_ServiceLocator().
                    getSB_IES_IES_ImportComEpuSimMatPaidSrvPort();
        }catch (ServiceException e) {
            if(e.getLinkedCause()!=null)
                e.getLinkedCause().printStackTrace();
            System.err.println("JAX-RPC ServiceException caught: " + e);
        }
        binding.setTimeout(60000);
        SB_IES_IES_ImportComEpuSimMatPaidSrvResponse value;

        SB_IES_IES_ImportComEpuSimMatPaidSrvRequest request = new SB_IES_IES_ImportComEpuSimMatPaidSrvRequest();
        MsgHeader header = new MsgHeader();
        request.setMsgHeader(header);
        SB_IES_IES_ImportComEpuSimMatPaidSrvInputItem[] items = new SB_IES_IES_ImportComEpuSimMatPaidSrvInputItem[2];//大小有待确定
        request.setSB_IES_IES_ImportComEpuSimMatPaidSrvInputCollection(items);

        try{
            value = binding.process(request);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
