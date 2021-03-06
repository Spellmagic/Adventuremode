// MESSAGE GIMBAL_ERASE_FIRMWARE_AND_CONFIG PACKING
package huins.ex.com.MAVLink.ardupilotmega;
import huins.ex.com.MAVLink.MAVLinkPacket;
import huins.ex.com.MAVLink.Messages.MAVLinkMessage;
import huins.ex.com.MAVLink.Messages.MAVLinkPayload;
        
/**
* 
            Commands the gimbal to erase its firmware image and flash configuration, leaving only the bootloader.  The gimbal will then reboot into the bootloader,
            ready for the load of a new application firmware image.  Erasing the flash configuration will cause the gimbal to re-perform axis calibration when a
            new firmware image is loaded, and will cause all tuning parameters to return to their factory defaults.  WARNING: sending this command will render a
            gimbal inoperable until a new firmware image is loaded onto it.  For this reason, a particular "knock" value must be sent for the command to take effect.
            Use this command at your own risk
        
*/
public class msg_gimbal_erase_firmware_and_config extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_GIMBAL_ERASE_FIRMWARE_AND_CONFIG = 208;
    public static final int MAVLINK_MSG_LENGTH = 6;
    private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_ERASE_FIRMWARE_AND_CONFIG;


     	
    /**
    * Knock value to confirm this is a valid request
    */
    public int knock;
     	
    /**
    * System ID
    */
    public byte target_system;
     	
    /**
    * Component ID
    */
    public byte target_component;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket();
        packet.len = MAVLINK_MSG_LENGTH;
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_GIMBAL_ERASE_FIRMWARE_AND_CONFIG;
        		packet.payload.putInt(knock);
        		packet.payload.putByte(target_system);
        		packet.payload.putByte(target_component);
        
        return packet;
    }

    /**
    * Decode a gimbal_erase_firmware_and_config message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        	    
        this.knock = payload.getInt();
        	    
        this.target_system = payload.getByte();
        	    
        this.target_component = payload.getByte();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_gimbal_erase_firmware_and_config(){
        msgid = MAVLINK_MSG_ID_GIMBAL_ERASE_FIRMWARE_AND_CONFIG;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_gimbal_erase_firmware_and_config(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_GIMBAL_ERASE_FIRMWARE_AND_CONFIG;
        unpack(mavLinkPacket.payload);        
    }

          
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_GIMBAL_ERASE_FIRMWARE_AND_CONFIG -"+" knock:"+knock+" target_system:"+target_system+" target_component:"+target_component+"";
    }
}
        