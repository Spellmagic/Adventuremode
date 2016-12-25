/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\KJH\\OneDrive\\GCST\\app\\src\\main\\aidl\\huins\\ex\\service\\GCSinterface\\IFlightInterface.aidl
 */
package huins.ex.service.GCSinterface;
public interface IFlightInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements huins.ex.service.GCSinterface.IFlightInterface
{
private static final java.lang.String DESCRIPTOR = "huins.ex.service.GCSinterface.IFlightInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an huins.ex.service.GCSinterface.IFlightInterface interface,
 * generating a proxy if needed.
 */
public static huins.ex.service.GCSinterface.IFlightInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof huins.ex.service.GCSinterface.IFlightInterface))) {
return ((huins.ex.service.GCSinterface.IFlightInterface)iin);
}
return new huins.ex.service.GCSinterface.IFlightInterface.Stub.Proxy(obj);
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
case TRANSACTION_getAttribute:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.Bundle _result = this.getAttribute(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_performAction:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.proto.action.Action _arg0;
if ((0!=data.readInt())) {
_arg0 = huins.ex.proto.action.Action.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.performAction(_arg0);
reply.writeNoException();
if ((_arg0!=null)) {
reply.writeInt(1);
_arg0.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_performAsyncAction:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.proto.action.Action _arg0;
if ((0!=data.readInt())) {
_arg0 = huins.ex.proto.action.Action.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.performAsyncAction(_arg0);
return true;
}
case TRANSACTION_addAttributesObserver:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.proto.IObserver _arg0;
_arg0 = huins.ex.proto.IObserver.Stub.asInterface(data.readStrongBinder());
this.addAttributesObserver(_arg0);
return true;
}
case TRANSACTION_removeAttributesObserver:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.proto.IObserver _arg0;
_arg0 = huins.ex.proto.IObserver.Stub.asInterface(data.readStrongBinder());
this.removeAttributesObserver(_arg0);
return true;
}
case TRANSACTION_addMavlinkObserver:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.proto.IMavlinkObserver _arg0;
_arg0 = huins.ex.proto.IMavlinkObserver.Stub.asInterface(data.readStrongBinder());
this.addMavlinkObserver(_arg0);
return true;
}
case TRANSACTION_removeMavlinkObserver:
{
data.enforceInterface(DESCRIPTOR);
huins.ex.proto.IMavlinkObserver _arg0;
_arg0 = huins.ex.proto.IMavlinkObserver.Stub.asInterface(data.readStrongBinder());
this.removeMavlinkObserver(_arg0);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements huins.ex.service.GCSinterface.IFlightInterface
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
@Override public android.os.Bundle getAttribute(java.lang.String attributeType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(attributeType);
mRemote.transact(Stub.TRANSACTION_getAttribute, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.os.Bundle.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void performAction(huins.ex.proto.action.Action action) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((action!=null)) {
_data.writeInt(1);
action.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_performAction, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
action.readFromParcel(_reply);
}
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void performAsyncAction(huins.ex.proto.action.Action action) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((action!=null)) {
_data.writeInt(1);
action.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_performAsyncAction, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addAttributesObserver(huins.ex.proto.IObserver observer) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((observer!=null))?(observer.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addAttributesObserver, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void removeAttributesObserver(huins.ex.proto.IObserver observer) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((observer!=null))?(observer.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_removeAttributesObserver, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addMavlinkObserver(huins.ex.proto.IMavlinkObserver observer) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((observer!=null))?(observer.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addMavlinkObserver, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void removeMavlinkObserver(huins.ex.proto.IMavlinkObserver observer) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((observer!=null))?(observer.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_removeMavlinkObserver, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_getAttribute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_performAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_performAsyncAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_addAttributesObserver = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_removeAttributesObserver = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_addMavlinkObserver = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_removeMavlinkObserver = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
public android.os.Bundle getAttribute(java.lang.String attributeType) throws android.os.RemoteException;
public void performAction(huins.ex.proto.action.Action action) throws android.os.RemoteException;
public void performAsyncAction(huins.ex.proto.action.Action action) throws android.os.RemoteException;
public void addAttributesObserver(huins.ex.proto.IObserver observer) throws android.os.RemoteException;
public void removeAttributesObserver(huins.ex.proto.IObserver observer) throws android.os.RemoteException;
public void addMavlinkObserver(huins.ex.proto.IMavlinkObserver observer) throws android.os.RemoteException;
public void removeMavlinkObserver(huins.ex.proto.IMavlinkObserver observer) throws android.os.RemoteException;
}
