package DRRSApp;

/**
* DRRSApp/DRRSHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DRRSidl.idl
* Tuesday, October 19, 2021 12:12:48 o'clock AM EDT
*/

public final class DRRSHolder implements org.omg.CORBA.portable.Streamable
{
  public DRRSApp.DRRS value = null;

  public DRRSHolder ()
  {
  }

  public DRRSHolder (DRRSApp.DRRS initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = DRRSApp.DRRSHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    DRRSApp.DRRSHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return DRRSApp.DRRSHelper.type ();
  }

}
