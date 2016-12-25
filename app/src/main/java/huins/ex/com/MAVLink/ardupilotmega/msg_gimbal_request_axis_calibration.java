// MESSAGE GIMBAL_REQUEST_AXIS_CALIBRATION PACKING
package huins.ex.com.MAVLink.ardupilotmega;
import huins.ex.com.MAVLink.MAVLinkPacket;
import huins.ex.com.MAVLink.Messages.MAVLinkMessage;
import huins.ex.com.MAVLink.Messages.MAVLinkPayload;
        
/**
* 
			Requests any currently uncalibrated gimbal axes to run the axis calibration procedure.  An axis is considered uncalibrated if its commutation calibration
			slope and intercept are 0
		
*/
public class msg_gimbal_request_axis_calibration extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_GIMBAL_REQUEST_AXIS_CALIBRATION = 213;
    public static final int MAVLINK_MSG_LENGTH = 2;
    private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_REQUEST_AXIS_CALIBRATION;


     	
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
        packet.msgid = MAVLINK_MSG_ID_GIMBAL_REQUEST_AXIS_CALIBRATION;
        		packet.payload.putByte(target_system);
        		packet.payload.putByte(target_component);
        
        return packet;
    }

    /**
    * Decode a gimbal_request_axis_calibration message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        	    
        this.target_system = payload.getByte();
        	    
        this.target_component = payload.getByte();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_gimbal_request_axis_calibration(){
        msgid = MAVLINK_MSG_ID_GIMBAL_REQUEST_AXIS_CALIBRATION;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_gimbal_request_axis_calibration(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_GIMBAL_REQUEST_AXIS_CALIBRATION;
        unpack(mavLinkPacket.payload);        
    }

        
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_GIMBAL_REQUEST_AXIS_CALIBRATION -"+" target_system:"+target_system+" target_component:"+target_component+"";
    }
}
        