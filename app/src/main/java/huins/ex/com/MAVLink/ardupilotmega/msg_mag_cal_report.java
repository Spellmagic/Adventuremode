// MESSAGE MAG_CAL_REPORT PACKING
package huins.ex.com.MAVLink.ardupilotmega;
import huins.ex.com.MAVLink.MAVLinkPacket;
import huins.ex.com.MAVLink.Messages.MAVLinkMessage;
import huins.ex.com.MAVLink.Messages.MAVLinkPayload;
        
/**
* Reports results of completed compass calibration. Sent until MAG_CAL_ACK received.
*/
public class msg_mag_cal_report extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_MAG_CAL_REPORT = 192;
    public static final int MAVLINK_MSG_LENGTH = 44;
    private static final long serialVersionUID = MAVLINK_MSG_ID_MAG_CAL_REPORT;


     	
    /**
    * RMS milligauss residuals
    */
    public float fitness;
     	
    /**
    * X offset
    */
    public float ofs_x;
     	
    /**
    * Y offset
    */
    public float ofs_y;
     	
    /**
    * Z offset
    */
    public float ofs_z;
     	
    /**
    * X diagonal (matrix 11)
    */
    public float diag_x;
     	
    /**
    * Y diagonal (matrix 22)
    */
    public float diag_y;
     	
    /**
    * Z diagonal (matrix 33)
    */
    public float diag_z;
     	
    /**
    * X off-diagonal (matrix 12 and 21)
    */
    public float offdiag_x;
     	
    /**
    * Y off-diagonal (matrix 13 and 31)
    */
    public float offdiag_y;
     	
    /**
    * Z off-diagonal (matrix 32 and 23)
    */
    public float offdiag_z;
     	
    /**
    * Compass being calibrated
    */
    public byte compass_id;
     	
    /**
    * Bitmask of compasses being calibrated
    */
    public byte cal_mask;
     	
    /**
    * Status (see MAG_CAL_STATUS enum)
    */
    public byte cal_status;
     	
    /**
    * 0=requires a MAV_CMD_DO_ACCEPT_MAG_CAL, 1=saved to parameters
    */
    public byte autosaved;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket();
        packet.len = MAVLINK_MSG_LENGTH;
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_MAG_CAL_REPORT;
        		packet.payload.putFloat(fitness);
        		packet.payload.putFloat(ofs_x);
        		packet.payload.putFloat(ofs_y);
        		packet.payload.putFloat(ofs_z);
        		packet.payload.putFloat(diag_x);
        		packet.payload.putFloat(diag_y);
        		packet.payload.putFloat(diag_z);
        		packet.payload.putFloat(offdiag_x);
        		packet.payload.putFloat(offdiag_y);
        		packet.payload.putFloat(offdiag_z);
        		packet.payload.putByte(compass_id);
        		packet.payload.putByte(cal_mask);
        		packet.payload.putByte(cal_status);
        		packet.payload.putByte(autosaved);
        
        return packet;
    }

    /**
    * Decode a mag_cal_report message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        	    
        this.fitness = payload.getFloat();
        	    
        this.ofs_x = payload.getFloat();
        	    
        this.ofs_y = payload.getFloat();
        	    
        this.ofs_z = payload.getFloat();
        	    
        this.diag_x = payload.getFloat();
        	    
        this.diag_y = payload.getFloat();
        	    
        this.diag_z = payload.getFloat();
        	    
        this.offdiag_x = payload.getFloat();
        	    
        this.offdiag_y = payload.getFloat();
        	    
        this.offdiag_z = payload.getFloat();
        	    
        this.compass_id = payload.getByte();
        	    
        this.cal_mask = payload.getByte();
        	    
        this.cal_status = payload.getByte();
        	    
        this.autosaved = payload.getByte();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_mag_cal_report(){
        msgid = MAVLINK_MSG_ID_MAG_CAL_REPORT;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_mag_cal_report(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_MAG_CAL_REPORT;
        unpack(mavLinkPacket.payload);        
    }

                                
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_MAG_CAL_REPORT -"+" fitness:"+fitness+" ofs_x:"+ofs_x+" ofs_y:"+ofs_y+" ofs_z:"+ofs_z+" diag_x:"+diag_x+" diag_y:"+diag_y+" diag_z:"+diag_z+" offdiag_x:"+offdiag_x+" offdiag_y:"+offdiag_y+" offdiag_z:"+offdiag_z+" compass_id:"+compass_id+" cal_mask:"+cal_mask+" cal_status:"+cal_status+" autosaved:"+autosaved+"";
    }
}
        