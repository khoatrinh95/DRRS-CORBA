package AuthenticationApp;

/**
* AuthenticationApp/AuthenticationHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Authentication.idl
* Wednesday, October 13, 2021 10:02:53 o'clock PM EDT
*/

public final class AuthenticationHolder implements org.omg.CORBA.portable.Streamable
{
  public AuthenticationApp.Authentication value = null;

  public AuthenticationHolder ()
  {
  }

  public AuthenticationHolder (AuthenticationApp.Authentication initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AuthenticationApp.AuthenticationHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AuthenticationApp.AuthenticationHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AuthenticationApp.AuthenticationHelper.type ();
  }

}
