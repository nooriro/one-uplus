// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: carriersettings.proto

package com.nooriro.oneuplus.proto.googlecarrier;

public interface CarrierSettingsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:CarrierSettings)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <pre>
   * unique key
   * </pre>
   *
   * <code>required string canonicalName = 1;</code>
   * @return Whether the canonicalName field is set.
   */
  boolean hasCanonicalName();
  /**
   * <pre>
   * unique key
   * </pre>
   *
   * <code>required string canonicalName = 1;</code>
   * @return The canonicalName.
   */
  java.lang.String getCanonicalName();
  /**
   * <pre>
   * unique key
   * </pre>
   *
   * <code>required string canonicalName = 1;</code>
   * @return The bytes for canonicalName.
   */
  com.google.protobuf.ByteString
      getCanonicalNameBytes();

  /**
   * <code>optional int64 version = 2;</code>
   * @return Whether the version field is set.
   */
  boolean hasVersion();
  /**
   * <code>optional int64 version = 2;</code>
   * @return The version.
   */
  long getVersion();

  /**
   * <code>optional .CarrierApns apns = 3;</code>
   * @return Whether the apns field is set.
   */
  boolean hasApns();
  /**
   * <code>optional .CarrierApns apns = 3;</code>
   * @return The apns.
   */
  com.nooriro.oneuplus.proto.googlecarrier.CarrierApns getApns();

  /**
   * <code>optional .CarrierConfig configs = 4;</code>
   * @return Whether the configs field is set.
   */
  boolean hasConfigs();
  /**
   * <code>optional .CarrierConfig configs = 4;</code>
   * @return The configs.
   */
  com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig getConfigs();

  /**
   * <code>optional .VendorConfigs vendorConfigs = 5;</code>
   * @return Whether the vendorConfigs field is set.
   */
  boolean hasVendorConfigs();
  /**
   * <code>optional .VendorConfigs vendorConfigs = 5;</code>
   * @return The vendorConfigs.
   */
  com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs getVendorConfigs();
}
