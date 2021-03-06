// MESSAGE GOPRO_SET_REQUEST PACKING
package huins.ex.com.MAVLink.ardupilotmega;
import huins.ex.com.MAVLink.MAVLinkPacket;
import huins.ex.com.MAVLink.Messages.MAVLinkMessage;
import huins.ex.com.MAVLink.Messages.MAVLinkPayload;
        
/**
* Request to set a GOPRO_COMMAND with a desired
*/
public class msg_gopro_set_request extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_GOPRO_SET_REQUEST = 218;
    public static final int MAVLINK_MSG_LENGTH = 4;
    private static final long serialVersionUID = MAVLINK_MSG_ID_GOPRO_SET_REQUEST;


     	
    /**
    * System ID
    */
    public byte target_system;
     	
    /**
    * Component ID
    */
    public byte target_component;
     	
    /**
    * Command ID
    */
    public byte cmd_id;
     	
    /**
    * Value
    */
    public byte value;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket();
        packet.len = MAVLINK_MSG_LENGTH;
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_GOPRO_SET_REQUEST;
        		packet.payload.putByte(target_system);
        		packet.payload.putByte(target_component);
        		packet.payload.putByte(cmd_id);
        		packet.payload.putByte(value);
        
        return packet;
    }

    /**
    * Decode a gopro_set_request message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        	    
        this.target_system = payload.getByte();
        	    
        this.target_component = payload.getByte();
        	    
        this.cmd_id = payload.getByte();
        	    
        this.value = payload.getByte();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_gopro_set_request(){
        msgid = MAVLINK_MSG_ID_GOPRO_SET_REQUEST;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_gopro_set_request(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_GOPRO_SET_REQUEST;
        unpack(mavLinkPacket.payload);        
    }

            
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_GOPRO_SET_REQUEST -"+" target_system:"+target_system+" target_component:"+target_component+" cmd_id:"+cmd_id+" value:"+value+"";
    }
}
        