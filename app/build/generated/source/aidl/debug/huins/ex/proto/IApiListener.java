/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\KJH\\OneDrive\\GCST\\app\\src\\main\\aidl\\huins\\ex\\proto\\IApiListener.aidl
 */
package huins.ex.proto;
public interface IApiListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements huins.ex.proto.IApiListener
{
private static final java.lang.String DESCRIPTOR = "huins.ex.proto.IApiListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an huins.ex.proto.IApiListener interface,
 * generating a proxy if needed.
 */
public static huins.ex.proto.IApiListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof huins.ex.proto.IApiListener))) {
return ((huins.ex.proto.IApiListener)iin);
}
return new huins.ex.proto.IApiListener.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getApiVersionCode:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getApiVersionCode();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_onConnectionFailed:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.proto.connection.ConnectionResult _arg0;
if ((0!=data.readInt())) {
_arg0 = huins.ex.proto.connection.ConnectionResult.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onConnectionFailed(_arg0);
return true;
}
case TRANSACTION_getClientVersionCode:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getClientVersionCode();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements huins.ex.proto.IApiListener
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int getApiVersionCode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getApiVersionCode, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void onConnectionFailed(huins.ex.proto.connection.ConnectionResult result) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((result!=null)) {
_data.writeInt(1);
result.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onConnectionFailed, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public int getClientVersionCode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getClientVersionCode, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getApiVersionCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onConnectionFailed = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getClientVersionCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public int getApiVersionCode() throws android.os.RemoteException;
public void onConnectionFailed(huins.ex.proto.connection.ConnectionResult result) throws android.os.RemoteException;
public int getClientVersionCode() throws android.os.RemoteException;
}
