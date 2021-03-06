package DRRSApp;


/**
* DRRSApp/_DRRSStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DRRSidl.idl
* Tuesday, October 19, 2021 12:12:48 o'clock AM EDT
*/

public class _DRRSStub extends org.omg.CORBA.portable.ObjectImpl implements DRRSApp.DRRS
{

  public String createRoom (String room_Number, String date, String beginTime, String endTime)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createRoom", true);
                $out.write_string (room_Number);
                $out.write_string (date);
                $out.write_string (beginTime);
                $out.write_string (endTime);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return createRoom (room_Number, date, beginTime, endTime        );
            } finally {
                _releaseReply ($in);
            }
  } // createRoom

  public String deleteRoom (String room_Number, String date, String[] listOfTimeSlotsIndexFromInputString)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteRoom", true);
                $out.write_string (room_Number);
                $out.write_string (date);
                DRRSApp.StringListHelper.write ($out, listOfTimeSlotsIndexFromInputString);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return deleteRoom (room_Number, date, listOfTimeSlotsIndexFromInputString        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteRoom

  public String bookRoom (String studentID, String campusName, String roomNumber, String date, int timeslot)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("bookRoom", true);
                $out.write_string (studentID);
                $out.write_string (campusName);
                $out.write_string (roomNumber);
                $out.write_string (date);
                $out.write_long (timeslot);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return bookRoom (studentID, campusName, roomNumber, date, timeslot        );
            } finally {
                _releaseReply ($in);
            }
  } // bookRoom

  public boolean overLimit (String studentID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("overLimit", true);
                $out.write_string (studentID);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return overLimit (studentID        );
            } finally {
                _releaseReply ($in);
            }
  } // overLimit

  public String[] getTimeSlots (String date, String room, String campus)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getTimeSlots", true);
                $out.write_string (date);
                $out.write_string (room);
                $out.write_string (campus);
                $in = _invoke ($out);
                String $result[] = DRRSApp.StringListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getTimeSlots (date, room, campus        );
            } finally {
                _releaseReply ($in);
            }
  } // getTimeSlots

  public String cancelBooking (String bookingID, String studentID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("cancelBooking", true);
                $out.write_string (bookingID);
                $out.write_string (studentID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return cancelBooking (bookingID, studentID        );
            } finally {
                _releaseReply ($in);
            }
  } // cancelBooking

  public String changeReservation (String studentID, String bookingID, String newCampusName, String date, String newRoomNo, String newTimeSlot)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("changeReservation", true);
                $out.write_string (studentID);
                $out.write_string (bookingID);
                $out.write_string (newCampusName);
                $out.write_string (date);
                $out.write_string (newRoomNo);
                $out.write_string (newTimeSlot);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return changeReservation (studentID, bookingID, newCampusName, date, newRoomNo, newTimeSlot        );
            } finally {
                _releaseReply ($in);
            }
  } // changeReservation

  public void shutdown ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("shutdown", false);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                shutdown (        );
            } finally {
                _releaseReply ($in);
            }
  } // shutdown

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:DRRSApp/DRRS:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _DRRSStub
