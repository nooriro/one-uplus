// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: carriersettings.proto

package com.nooriro.oneuplus.proto.googlecarrier;

public interface ApnItemOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ApnItem)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>optional string name = 1;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>optional string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>optional string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>required string value = 2;</code>
   * @return Whether the value field is set.
   */
  boolean hasValue();
  /**
   * <code>required string value = 2;</code>
   * @return The value.
   */
  java.lang.String getValue();
  /**
   * <code>required string value = 2;</code>
   * @return The bytes for value.
   */
  com.google.protobuf.ByteString
      getValueBytes();

  /**
   * <code>repeated .ApnItem.ApnType type = 3;</code>
   * @return A list containing the type.
   */
  java.util.List<com.nooriro.oneuplus.proto.googlecarrier.ApnItem.ApnType> getTypeList();
  /**
   * <code>repeated .ApnItem.ApnType type = 3;</code>
   * @return The count of type.
   */
  int getTypeCount();
  /**
   * <code>repeated .ApnItem.ApnType type = 3;</code>
   * @param index The index of the element to return.
   * @return The type at the given index.
   */
  com.nooriro.oneuplus.proto.googlecarrier.ApnItem.ApnType getType(int index);

  /**
   * <code>optional string bearerBitmask = 4;</code>
   * @return Whether the bearerBitmask field is set.
   */
  boolean hasBearerBitmask();
  /**
   * <code>optional string bearerBitmask = 4;</code>
   * @return The bearerBitmask.
   */
  java.lang.String getBearerBitmask();
  /**
   * <code>optional string bearerBitmask = 4;</code>
   * @return The bytes for bearerBitmask.
   */
  com.google.protobuf.ByteString
      getBearerBitmaskBytes();

  /**
   * <code>optional string server = 5;</code>
   * @return Whether the server field is set.
   */
  boolean hasServer();
  /**
   * <code>optional string server = 5;</code>
   * @return The server.
   */
  java.lang.String getServer();
  /**
   * <code>optional string server = 5;</code>
   * @return The bytes for server.
   */
  com.google.protobuf.ByteString
      getServerBytes();

  /**
   * <code>optional string proxy = 6;</code>
   * @return Whether the proxy field is set.
   */
  boolean hasProxy();
  /**
   * <code>optional string proxy = 6;</code>
   * @return The proxy.
   */
  java.lang.String getProxy();
  /**
   * <code>optional string proxy = 6;</code>
   * @return The bytes for proxy.
   */
  com.google.protobuf.ByteString
      getProxyBytes();

  /**
   * <code>optional string port = 7;</code>
   * @return Whether the port field is set.
   */
  boolean hasPort();
  /**
   * <code>optional string port = 7;</code>
   * @return The port.
   */
  java.lang.String getPort();
  /**
   * <code>optional string port = 7;</code>
   * @return The bytes for port.
   */
  com.google.protobuf.ByteString
      getPortBytes();

  /**
   * <code>optional string user = 8;</code>
   * @return Whether the user field is set.
   */
  boolean hasUser();
  /**
   * <code>optional string user = 8;</code>
   * @return The user.
   */
  java.lang.String getUser();
  /**
   * <code>optional string user = 8;</code>
   * @return The bytes for user.
   */
  com.google.protobuf.ByteString
      getUserBytes();

  /**
   * <code>optional string password = 9;</code>
   * @return Whether the password field is set.
   */
  boolean hasPassword();
  /**
   * <code>optional string password = 9;</code>
   * @return The password.
   */
  java.lang.String getPassword();
  /**
   * <code>optional string password = 9;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();

  /**
   * <code>optional int32 authtype = 10;</code>
   * @return Whether the authtype field is set.
   */
  boolean hasAuthtype();
  /**
   * <code>optional int32 authtype = 10;</code>
   * @return The authtype.
   */
  int getAuthtype();

  /**
   * <code>optional string mmsc = 11;</code>
   * @return Whether the mmsc field is set.
   */
  boolean hasMmsc();
  /**
   * <code>optional string mmsc = 11;</code>
   * @return The mmsc.
   */
  java.lang.String getMmsc();
  /**
   * <code>optional string mmsc = 11;</code>
   * @return The bytes for mmsc.
   */
  com.google.protobuf.ByteString
      getMmscBytes();

  /**
   * <code>optional string mmscProxy = 12;</code>
   * @return Whether the mmscProxy field is set.
   */
  boolean hasMmscProxy();
  /**
   * <code>optional string mmscProxy = 12;</code>
   * @return The mmscProxy.
   */
  java.lang.String getMmscProxy();
  /**
   * <code>optional string mmscProxy = 12;</code>
   * @return The bytes for mmscProxy.
   */
  com.google.protobuf.ByteString
      getMmscProxyBytes();

  /**
   * <code>optional string mmscProxyPort = 13;</code>
   * @return Whether the mmscProxyPort field is set.
   */
  boolean hasMmscProxyPort();
  /**
   * <code>optional string mmscProxyPort = 13;</code>
   * @return The mmscProxyPort.
   */
  java.lang.String getMmscProxyPort();
  /**
   * <code>optional string mmscProxyPort = 13;</code>
   * @return The bytes for mmscProxyPort.
   */
  com.google.protobuf.ByteString
      getMmscProxyPortBytes();

  /**
   * <code>optional .ApnItem.Protocol protocol = 14;</code>
   * @return Whether the protocol field is set.
   */
  boolean hasProtocol();
  /**
   * <code>optional .ApnItem.Protocol protocol = 14;</code>
   * @return The protocol.
   */
  com.nooriro.oneuplus.proto.googlecarrier.ApnItem.Protocol getProtocol();

  /**
   * <code>optional .ApnItem.Protocol roamingProtocol = 15;</code>
   * @return Whether the roamingProtocol field is set.
   */
  boolean hasRoamingProtocol();
  /**
   * <code>optional .ApnItem.Protocol roamingProtocol = 15;</code>
   * @return The roamingProtocol.
   */
  com.nooriro.oneuplus.proto.googlecarrier.ApnItem.Protocol getRoamingProtocol();

  /**
   * <code>optional int32 mtu = 16;</code>
   * @return Whether the mtu field is set.
   */
  boolean hasMtu();
  /**
   * <code>optional int32 mtu = 16;</code>
   * @return The mtu.
   */
  int getMtu();

  /**
   * <code>optional int32 profileId = 17;</code>
   * @return Whether the profileId field is set.
   */
  boolean hasProfileId();
  /**
   * <code>optional int32 profileId = 17;</code>
   * @return The profileId.
   */
  int getProfileId();

  /**
   * <code>optional int32 maxConns = 18;</code>
   * @return Whether the maxConns field is set.
   */
  boolean hasMaxConns();
  /**
   * <code>optional int32 maxConns = 18;</code>
   * @return The maxConns.
   */
  int getMaxConns();

  /**
   * <pre>
   * unused
   * </pre>
   *
   * <code>optional int32 waitTime = 19;</code>
   * @return Whether the waitTime field is set.
   */
  boolean hasWaitTime();
  /**
   * <pre>
   * unused
   * </pre>
   *
   * <code>optional int32 waitTime = 19;</code>
   * @return The waitTime.
   */
  int getWaitTime();

  /**
   * <code>optional int32 maxConnsTime = 20;</code>
   * @return Whether the maxConnsTime field is set.
   */
  boolean hasMaxConnsTime();
  /**
   * <code>optional int32 maxConnsTime = 20;</code>
   * @return The maxConnsTime.
   */
  int getMaxConnsTime();

  /**
   * <code>optional bool carrierEnabled = 21;</code>
   * @return Whether the carrierEnabled field is set.
   */
  boolean hasCarrierEnabled();
  /**
   * <code>optional bool carrierEnabled = 21;</code>
   * @return The carrierEnabled.
   */
  boolean getCarrierEnabled();

  /**
   * <code>optional bool modemCognitive = 22;</code>
   * @return Whether the modemCognitive field is set.
   */
  boolean hasModemCognitive();
  /**
   * <code>optional bool modemCognitive = 22;</code>
   * @return The modemCognitive.
   */
  boolean getModemCognitive();

  /**
   * <code>optional bool userVisible = 23;</code>
   * @return Whether the userVisible field is set.
   */
  boolean hasUserVisible();
  /**
   * <code>optional bool userVisible = 23;</code>
   * @return The userVisible.
   */
  boolean getUserVisible();

  /**
   * <code>optional bool userEditable = 24;</code>
   * @return Whether the userEditable field is set.
   */
  boolean hasUserEditable();
  /**
   * <code>optional bool userEditable = 24;</code>
   * @return The userEditable.
   */
  boolean getUserEditable();

  /**
   * <pre>
   * unused
   * </pre>
   *
   * <code>optional int32 apnSetId = 25;</code>
   * @return Whether the apnSetId field is set.
   */
  boolean hasApnSetId();
  /**
   * <pre>
   * unused
   * </pre>
   *
   * <code>optional int32 apnSetId = 25;</code>
   * @return The apnSetId.
   */
  int getApnSetId();

  /**
   * <pre>
   * unused
   * </pre>
   *
   * <code>optional .ApnItem.Xlat skip464Xlat = 26;</code>
   * @return Whether the skip464Xlat field is set.
   */
  boolean hasSkip464Xlat();
  /**
   * <pre>
   * unused
   * </pre>
   *
   * <code>optional .ApnItem.Xlat skip464Xlat = 26;</code>
   * @return The skip464Xlat.
   */
  com.nooriro.oneuplus.proto.googlecarrier.ApnItem.Xlat getSkip464Xlat();
}
