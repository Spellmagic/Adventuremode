/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\KJH\\OneDrive\\GCST\\app\\src\\main\\aidl\\huins\\ex\\proto\\IGCSServices.aidl
 */
package huins.ex.proto;
/**
* Used to establish connection with a drone.
*/
public interface IGCSServices extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements huins.ex.proto.IGCSServices
{
private static final java.lang.String DESCRIPTOR = "huins.ex.proto.IGCSServices";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an huins.ex.proto.IGCSServices interface,
 * generating a proxy if needed.
 */
public static huins.ex.proto.IGCSServices asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof huins.ex.proto.IGCSServices))) {
return ((huins.ex.proto.IGCSServices)iin);
}
return new huins.ex.proto.IGCSServices.Stub.Proxy(obj);
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
case TRANSACTION_getServiceVersionCode:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getServiceVersionCode();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_releaseFlightApi:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.service.GCSinterface.IFlightInterface _arg0;
_arg0 = huins.ex.service.GCSinterface.IFlightInterface.Stub.asInterface(data.readStrongBinder());
this.releaseFlightApi(_arg0);
reply.writeNoException();
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
case TRANSACTION_registerFlightApi:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.proto.IApiListener _arg0;
_arg0 = huins.ex.proto.IApiListener.Stub.asInterface(data.readStrongBinder());
java.lang.String _arg1;
_arg1 = data.readString();
huins.ex.service.GCSinterface.IFlightInterface _result = this.registerFlightApi(_arg0, _arg1);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getConnectedApps:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.Bundle[] _result = this.getConnectedApps(_arg0);
reply.writeNoException();
reply.writeTypedArray(_result, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements huins.ex.proto.IGCSServices
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
@Override public int getServiceVersionCode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getServiceVersionCode, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void releaseFlightApi(huins.ex.service.GCSinterface.IFlightInterface flightinterface) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((flightinterface!=null))?(flightinterface.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_releaseFlightApi, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
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
@Override public huins.ex.service.GCSinterface.IFlightInterface registerFlightApi(huins.ex.proto.IApiListener listener, java.lang.String appId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
huins.ex.service.GCSinterface.IFlightInterface _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
_data.writeString(appId);
mRemote.transact(Stub.TRANSACTION_registerFlightApi, _data, _reply, 0);
_reply.readException();
_result = huins.ex.service.GCSinterface.IFlightInterface.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.os.Bundle[] getConnectedApps(java.lang.String requesterId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(requesterId);
mRemote.transact(Stub.TRANSACTION_getConnectedApps, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArray(android.os.Bundle.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getServiceVersionCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_releaseFlightApi = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getApiVersionCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_registerFlightApi = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getConnectedApps = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
public int getServiceVersionCode() throws android.os.RemoteException;
public void releaseFlightApi(huins.ex.service.GCSinterface.IFlightInterface flightinterface) throws android.os.RemoteException;
public int getApiVersionCode() throws android.os.RemoteException;
public huins.ex.service.GCSinterface.IFlightInterface registerFlightApi(huins.ex.proto.IApiListener listener, java.lang.String appId) throws android.os.RemoteException;
public android.os.Bundle[] getConnectedApps(java.lang.String requesterId) throws android.os.RemoteException;
}
