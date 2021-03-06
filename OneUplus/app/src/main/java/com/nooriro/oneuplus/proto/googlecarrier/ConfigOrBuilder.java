// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: carriersettings.proto

package com.nooriro.oneuplus.proto.googlecarrier;

public interface ConfigOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Config)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>required string key = 1;</code>
   * @return Whether the key field is set.
   */
  boolean hasKey();
  /**
   * <code>required string key = 1;</code>
   * @return The key.
   */
  java.lang.String getKey();
  /**
   * <code>required string key = 1;</code>
   * @return The bytes for key.
   */
  com.google.protobuf.ByteString
      getKeyBytes();

  /**
   * <code>string textValue = 2;</code>
   * @return Whether the textValue field is set.
   */
  boolean hasTextValue();
  /**
   * <code>string textValue = 2;</code>
   * @return The textValue.
   */
  java.lang.String getTextValue();
  /**
   * <code>string textValue = 2;</code>
   * @return The bytes for textValue.
   */
  com.google.protobuf.ByteString
      getTextValueBytes();

  /**
   * <code>int32 intValue = 3;</code>
   * @return Whether the intValue field is set.
   */
  boolean hasIntValue();
  /**
   * <code>int32 intValue = 3;</code>
   * @return The intValue.
   */
  int getIntValue();

  /**
   * <code>int64 longValue = 4;</code>
   * @return Whether the longValue field is set.
   */
  boolean hasLongValue();
  /**
   * <code>int64 longValue = 4;</code>
   * @return The longValue.
   */
  long getLongValue();

  /**
   * <code>bool boolValue = 5;</code>
   * @return Whether the boolValue field is set.
   */
  boolean hasBoolValue();
  /**
   * <code>bool boolValue = 5;</code>
   * @return The boolValue.
   */
  boolean getBoolValue();

  /**
   * <code>.TextArray textArray = 6;</code>
   * @return Whether the textArray field is set.
   */
  boolean hasTextArray();
  /**
   * <code>.TextArray textArray = 6;</code>
   * @return The textArray.
   */
  com.nooriro.oneuplus.proto.googlecarrier.TextArray getTextArray();

  /**
   * <code>.IntArray intArray = 7;</code>
   * @return Whether the intArray field is set.
   */
  boolean hasIntArray();
  /**
   * <code>.IntArray intArray = 7;</code>
   * @return The intArray.
   */
  com.nooriro.oneuplus.proto.googlecarrier.IntArray getIntArray();

  public com.nooriro.oneuplus.proto.googlecarrier.Config.ValueCase getValueCase();
}
