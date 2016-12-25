// MESSAGE GIMBAL_FACTORY_PARAMETERS_LOADED PACKING
package huins.ex.com.MAVLink.ardupilotmega;
import huins.ex.com.MAVLink.MAVLinkPacket;
import huins.ex.com.MAVLink.Messages.MAVLinkMessage;
import huins.ex.com.MAVLink.Messages.MAVLinkPayload;
        
/**
* 
            Sent by the gimbal after the factory parameters are successfully loaded, to inform the factory software that the load is complete
        
*/
public class msg_gimbal_factory_parameters_loaded extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_GIMBAL_FACTORY_PARAMETERS_LOADED = 207;
    public static final int MAVLINK_MSG_LENGTH = 1;
    private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_FACTORY_PARAMETERS_LOADED;


     	
    /**
    * Dummy field because mavgen doesn't allow messages with no fields
    */
    public byte dummy;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket();
        packet.len = MAVLINK_MSG_LENGTH;
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_GIMBAL_FACTORY_PARAMETERS_LOADED;
        		packet.payload.putByte(dummy);
        
        return packet;
    }

    /**
    * Decode a gimbal_factory_parameters_loaded message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        	    
        this.dummy = payload.getByte();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_gimbal_factory_parameters_loaded(){
        msgid = MAVLINK_MSG_ID_GIMBAL_FACTORY_PARAMETERS_LOADED;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_gimbal_factory_parameters_loaded(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_GIMBAL_FACTORY_PARAMETERS_LOADED;
        unpack(mavLinkPacket.payload);        
    }

      
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_GIMBAL_FACTORY_PARAMETERS_LOADED -"+" dummy:"+dummy+"";
    }
}
        