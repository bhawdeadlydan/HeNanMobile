package ROut;

import javax.xml.rpc.ServiceException;

/**
 * Created by richard on 2015/12/7.
 */
public class Send {
    public static void process(){
        SB_IES_IES_ImportComEpuSimMatMStorRSendSrvBindingStub binding = null;
        try{
            binding = (SB_IES_IES_ImportComEpuSimMatMStorRSendSrvBindingStub)new SB_IES_IES_ImportComEpuSimMatMStorRSendSrv_ServiceLocator().
                    getSB_IES_IES_ImportComEpuSimMatMStorRSendSrvPort();
        }catch (ServiceException e) {
            if(e.getLinkedCause()!=null)
                e.getLinkedCause().printStackTrace();
            System.err.println("JAX-RPC ServiceException caught: " + e);
        }
        binding.setTimeout(60000);
        SB_IES_IES_ImportComEpuSimMatMStorRSendSrvResponse value;

        SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest request = new SB_IES_IES_ImportComEpuSimMatMStorRSendSrvRequest();
        MsgHeader header = new MsgHeader();
        request.setMsgHeader(header);
        SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputItem[] items = new SB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputItem[2];//大小有待确定
        request.setSB_IES_IES_ImportComEpuSimMatMStorRSendSrvInputCollection(items);

        try{
            value = binding.process(request);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
