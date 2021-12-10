// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: carriersettings.proto

package com.nooriro.oneuplus.proto.googlecarrier;

/**
 * Protobuf type {@code Config}
 */
public  final class Config extends
    com.google.protobuf.GeneratedMessageLite<
        Config, Config.Builder> implements
    // @@protoc_insertion_point(message_implements:Config)
    ConfigOrBuilder {
  private Config() {
    key_ = "";
  }
  private int bitField0_;
  private int valueCase_ = 0;
  private java.lang.Object value_;
  public enum ValueCase {
    TEXTVALUE(2),
    INTVALUE(3),
    LONGVALUE(4),
    BOOLVALUE(5),
    TEXTARRAY(6),
    INTARRAY(7),
    VALUE_NOT_SET(0);
    private final int value;
    private ValueCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static ValueCase valueOf(int value) {
      return forNumber(value);
    }

    public static ValueCase forNumber(int value) {
      switch (value) {
        case 2: return TEXTVALUE;
        case 3: return INTVALUE;
        case 4: return LONGVALUE;
        case 5: return BOOLVALUE;
        case 6: return TEXTARRAY;
        case 7: return INTARRAY;
        case 0: return VALUE_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  @java.lang.Override
  public ValueCase
  getValueCase() {
    return ValueCase.forNumber(
        valueCase_);
  }

  private void clearValue() {
    valueCase_ = 0;
    value_ = null;
  }

  public static final int KEY_FIELD_NUMBER = 1;
  private java.lang.String key_;
  /**
   * <code>required string key = 1;</code>
   * @return Whether the key field is set.
   */
  @java.lang.Override
  public boolean hasKey() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>required string key = 1;</code>
   * @return The key.
   */
  @java.lang.Override
  public java.lang.String getKey() {
    return key_;
  }
  /**
   * <code>required string key = 1;</code>
   * @return The bytes for key.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getKeyBytes() {
    return com.google.protobuf.ByteString.copyFromUtf8(key_);
  }
  /**
   * <code>required string key = 1;</code>
   * @param value The key to set.
   */
  private void setKey(
      java.lang.String value) {
    java.lang.Class<?> valueClass = value.getClass();
  bitField0_ |= 0x00000001;
    key_ = value;
  }
  /**
   * <code>required string key = 1;</code>
   */
  private void clearKey() {
    bitField0_ = (bitField0_ & ~0x00000001);
    key_ = getDefaultInstance().getKey();
  }
  /**
   * <code>required string key = 1;</code>
   * @param value The bytes for key to set.
   */
  private void setKeyBytes(
      com.google.protobuf.ByteString value) {
    key_ = value.toStringUtf8();
    bitField0_ |= 0x00000001;
  }

  public static final int TEXTVALUE_FIELD_NUMBER = 2;
  /**
   * <code>string textValue = 2;</code>
   * @return Whether the textValue field is set.
   */
  @java.lang.Override
  public boolean hasTextValue() {
    return valueCase_ == 2;
  }
  /**
   * <code>string textValue = 2;</code>
   * @return The textValue.
   */
  @java.lang.Override
  public java.lang.String getTextValue() {
    java.lang.String ref = "";
    if (valueCase_ == 2) {
      ref = (java.lang.String) value_;
    }
    return ref;
  }
  /**
   * <code>string textValue = 2;</code>
   * @return The bytes for textValue.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTextValueBytes() {
    java.lang.String ref = "";
    if (valueCase_ == 2) {
      ref = (java.lang.String) value_;
    }
    return com.google.protobuf.ByteString.copyFromUtf8(ref);
  }
  /**
   * <code>string textValue = 2;</code>
   * @param value The textValue to set.
   */
  private void setTextValue(
      java.lang.String value) {
    java.lang.Class<?> valueClass = value.getClass();
  valueCase_ = 2;
    value_ = value;
  }
  /**
   * <code>string textValue = 2;</code>
   */
  private void clearTextValue() {
    if (valueCase_ == 2) {
      valueCase_ = 0;
      value_ = null;
    }
  }
  /**
   * <code>string textValue = 2;</code>
   * @param value The bytes for textValue to set.
   */
  private void setTextValueBytes(
      com.google.protobuf.ByteString value) {
    value_ = value.toStringUtf8();
    valueCase_ = 2;
  }

  public static final int INTVALUE_FIELD_NUMBER = 3;
  /**
   * <code>int32 intValue = 3;</code>
   * @return Whether the intValue field is set.
   */
  @java.lang.Override
  public boolean hasIntValue() {
    return valueCase_ == 3;
  }
  /**
   * <code>int32 intValue = 3;</code>
   * @return The intValue.
   */
  @java.lang.Override
  public int getIntValue() {
    if (valueCase_ == 3) {
      return (java.lang.Integer) value_;
    }
    return 0;
  }
  /**
   * <code>int32 intValue = 3;</code>
   * @param value The intValue to set.
   */
  private void setIntValue(int value) {
    valueCase_ = 3;
    value_ = value;
  }
  /**
   * <code>int32 intValue = 3;</code>
   */
  private void clearIntValue() {
    if (valueCase_ == 3) {
      valueCase_ = 0;
      value_ = null;
    }
  }

  public static final int LONGVALUE_FIELD_NUMBER = 4;
  /**
   * <code>int64 longValue = 4;</code>
   * @return Whether the longValue field is set.
   */
  @java.lang.Override
  public boolean hasLongValue() {
    return valueCase_ == 4;
  }
  /**
   * <code>int64 longValue = 4;</code>
   * @return The longValue.
   */
  @java.lang.Override
  public long getLongValue() {
    if (valueCase_ == 4) {
      return (java.lang.Long) value_;
    }
    return 0L;
  }
  /**
   * <code>int64 longValue = 4;</code>
   * @param value The longValue to set.
   */
  private void setLongValue(long value) {
    valueCase_ = 4;
    value_ = value;
  }
  /**
   * <code>int64 longValue = 4;</code>
   */
  private void clearLongValue() {
    if (valueCase_ == 4) {
      valueCase_ = 0;
      value_ = null;
    }
  }

  public static final int BOOLVALUE_FIELD_NUMBER = 5;
  /**
   * <code>bool boolValue = 5;</code>
   * @return Whether the boolValue field is set.
   */
  @java.lang.Override
  public boolean hasBoolValue() {
    return valueCase_ == 5;
  }
  /**
   * <code>bool boolValue = 5;</code>
   * @return The boolValue.
   */
  @java.lang.Override
  public boolean getBoolValue() {
    if (valueCase_ == 5) {
      return (java.lang.Boolean) value_;
    }
    return false;
  }
  /**
   * <code>bool boolValue = 5;</code>
   * @param value The boolValue to set.
   */
  private void setBoolValue(boolean value) {
    valueCase_ = 5;
    value_ = value;
  }
  /**
   * <code>bool boolValue = 5;</code>
   */
  private void clearBoolValue() {
    if (valueCase_ == 5) {
      valueCase_ = 0;
      value_ = null;
    }
  }

  public static final int TEXTARRAY_FIELD_NUMBER = 6;
  /**
   * <code>.TextArray textArray = 6;</code>
   */
  @java.lang.Override
  public boolean hasTextArray() {
    return valueCase_ == 6;
  }
  /**
   * <code>.TextArray textArray = 6;</code>
   */
  @java.lang.Override
  public com.nooriro.oneuplus.proto.googlecarrier.TextArray getTextArray() {
    if (valueCase_ == 6) {
       return (com.nooriro.oneuplus.proto.googlecarrier.TextArray) value_;
    }
    return com.nooriro.oneuplus.proto.googlecarrier.TextArray.getDefaultInstance();
  }
  /**
   * <code>.TextArray textArray = 6;</code>
   */
  private void setTextArray(com.nooriro.oneuplus.proto.googlecarrier.TextArray value) {
    value.getClass();
  value_ = value;
    valueCase_ = 6;
  }
  /**
   * <code>.TextArray textArray = 6;</code>
   */
  private void mergeTextArray(com.nooriro.oneuplus.proto.googlecarrier.TextArray value) {
    value.getClass();
  if (valueCase_ == 6 &&
        value_ != com.nooriro.oneuplus.proto.googlecarrier.TextArray.getDefaultInstance()) {
      value_ = com.nooriro.oneuplus.proto.googlecarrier.TextArray.newBuilder((com.nooriro.oneuplus.proto.googlecarrier.TextArray) value_)
          .mergeFrom(value).buildPartial();
    } else {
      value_ = value;
    }
    valueCase_ = 6;
  }
  /**
   * <code>.TextArray textArray = 6;</code>
   */
  private void clearTextArray() {
    if (valueCase_ == 6) {
      valueCase_ = 0;
      value_ = null;
    }
  }

  public static final int INTARRAY_FIELD_NUMBER = 7;
  /**
   * <code>.IntArray intArray = 7;</code>
   */
  @java.lang.Override
  public boolean hasIntArray() {
    return valueCase_ == 7;
  }
  /**
   * <code>.IntArray intArray = 7;</code>
   */
  @java.lang.Override
  public com.nooriro.oneuplus.proto.googlecarrier.IntArray getIntArray() {
    if (valueCase_ == 7) {
       return (com.nooriro.oneuplus.proto.googlecarrier.IntArray) value_;
    }
    return com.nooriro.oneuplus.proto.googlecarrier.IntArray.getDefaultInstance();
  }
  /**
   * <code>.IntArray intArray = 7;</code>
   */
  private void setIntArray(com.nooriro.oneuplus.proto.googlecarrier.IntArray value) {
    value.getClass();
  value_ = value;
    valueCase_ = 7;
  }
  /**
   * <code>.IntArray intArray = 7;</code>
   */
  private void mergeIntArray(com.nooriro.oneuplus.proto.googlecarrier.IntArray value) {
    value.getClass();
  if (valueCase_ == 7 &&
        value_ != com.nooriro.oneuplus.proto.googlecarrier.IntArray.getDefaultInstance()) {
      value_ = com.nooriro.oneuplus.proto.googlecarrier.IntArray.newBuilder((com.nooriro.oneuplus.proto.googlecarrier.IntArray) value_)
          .mergeFrom(value).buildPartial();
    } else {
      value_ = value;
    }
    valueCase_ = 7;
  }
  /**
   * <code>.IntArray intArray = 7;</code>
   */
  private void clearIntArray() {
    if (valueCase_ == 7) {
      valueCase_ = 0;
      value_ = null;
    }
  }

  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.Config parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(com.nooriro.oneuplus.proto.googlecarrier.Config prototype) {
    return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code Config}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        com.nooriro.oneuplus.proto.googlecarrier.Config, Builder> implements
      // @@protoc_insertion_point(builder_implements:Config)
      com.nooriro.oneuplus.proto.googlecarrier.ConfigOrBuilder {
    // Construct using com.nooriro.oneuplus.proto.googlecarrier.Config.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }

    @java.lang.Override
    public ValueCase
        getValueCase() {
      return instance.getValueCase();
    }

    public Builder clearValue() {
      copyOnWrite();
      instance.clearValue();
      return this;
    }


    /**
     * <code>required string key = 1;</code>
     * @return Whether the key field is set.
     */
    @java.lang.Override
    public boolean hasKey() {
      return instance.hasKey();
    }
    /**
     * <code>required string key = 1;</code>
     * @return The key.
     */
    @java.lang.Override
    public java.lang.String getKey() {
      return instance.getKey();
    }
    /**
     * <code>required string key = 1;</code>
     * @return The bytes for key.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getKeyBytes() {
      return instance.getKeyBytes();
    }
    /**
     * <code>required string key = 1;</code>
     * @param value The key to set.
     * @return This builder for chaining.
     */
    public Builder setKey(
        java.lang.String value) {
      copyOnWrite();
      instance.setKey(value);
      return this;
    }
    /**
     * <code>required string key = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearKey() {
      copyOnWrite();
      instance.clearKey();
      return this;
    }
    /**
     * <code>required string key = 1;</code>
     * @param value The bytes for key to set.
     * @return This builder for chaining.
     */
    public Builder setKeyBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setKeyBytes(value);
      return this;
    }

    /**
     * <code>string textValue = 2;</code>
     * @return Whether the textValue field is set.
     */
    @java.lang.Override
    public boolean hasTextValue() {
      return instance.hasTextValue();
    }
    /**
     * <code>string textValue = 2;</code>
     * @return The textValue.
     */
    @java.lang.Override
    public java.lang.String getTextValue() {
      return instance.getTextValue();
    }
    /**
     * <code>string textValue = 2;</code>
     * @return The bytes for textValue.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getTextValueBytes() {
      return instance.getTextValueBytes();
    }
    /**
     * <code>string textValue = 2;</code>
     * @param value The textValue to set.
     * @return This builder for chaining.
     */
    public Builder setTextValue(
        java.lang.String value) {
      copyOnWrite();
      instance.setTextValue(value);
      return this;
    }
    /**
     * <code>string textValue = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTextValue() {
      copyOnWrite();
      instance.clearTextValue();
      return this;
    }
    /**
     * <code>string textValue = 2;</code>
     * @param value The bytes for textValue to set.
     * @return This builder for chaining.
     */
    public Builder setTextValueBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setTextValueBytes(value);
      return this;
    }

    /**
     * <code>int32 intValue = 3;</code>
     * @return Whether the intValue field is set.
     */
    @java.lang.Override
    public boolean hasIntValue() {
      return instance.hasIntValue();
    }
    /**
     * <code>int32 intValue = 3;</code>
     * @return The intValue.
     */
    @java.lang.Override
    public int getIntValue() {
      return instance.getIntValue();
    }
    /**
     * <code>int32 intValue = 3;</code>
     * @param value The intValue to set.
     * @return This builder for chaining.
     */
    public Builder setIntValue(int value) {
      copyOnWrite();
      instance.setIntValue(value);
      return this;
    }
    /**
     * <code>int32 intValue = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearIntValue() {
      copyOnWrite();
      instance.clearIntValue();
      return this;
    }

    /**
     * <code>int64 longValue = 4;</code>
     * @return Whether the longValue field is set.
     */
    @java.lang.Override
    public boolean hasLongValue() {
      return instance.hasLongValue();
    }
    /**
     * <code>int64 longValue = 4;</code>
     * @return The longValue.
     */
    @java.lang.Override
    public long getLongValue() {
      return instance.getLongValue();
    }
    /**
     * <code>int64 longValue = 4;</code>
     * @param value The longValue to set.
     * @return This builder for chaining.
     */
    public Builder setLongValue(long value) {
      copyOnWrite();
      instance.setLongValue(value);
      return this;
    }
    /**
     * <code>int64 longValue = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearLongValue() {
      copyOnWrite();
      instance.clearLongValue();
      return this;
    }

    /**
     * <code>bool boolValue = 5;</code>
     * @return Whether the boolValue field is set.
     */
    @java.lang.Override
    public boolean hasBoolValue() {
      return instance.hasBoolValue();
    }
    /**
     * <code>bool boolValue = 5;</code>
     * @return The boolValue.
     */
    @java.lang.Override
    public boolean getBoolValue() {
      return instance.getBoolValue();
    }
    /**
     * <code>bool boolValue = 5;</code>
     * @param value The boolValue to set.
     * @return This builder for chaining.
     */
    public Builder setBoolValue(boolean value) {
      copyOnWrite();
      instance.setBoolValue(value);
      return this;
    }
    /**
     * <code>bool boolValue = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearBoolValue() {
      copyOnWrite();
      instance.clearBoolValue();
      return this;
    }

    /**
     * <code>.TextArray textArray = 6;</code>
     */
    @java.lang.Override
    public boolean hasTextArray() {
      return instance.hasTextArray();
    }
    /**
     * <code>.TextArray textArray = 6;</code>
     */
    @java.lang.Override
    public com.nooriro.oneuplus.proto.googlecarrier.TextArray getTextArray() {
      return instance.getTextArray();
    }
    /**
     * <code>.TextArray textArray = 6;</code>
     */
    public Builder setTextArray(com.nooriro.oneuplus.proto.googlecarrier.TextArray value) {
      copyOnWrite();
      instance.setTextArray(value);
      return this;
    }
    /**
     * <code>.TextArray textArray = 6;</code>
     */
    public Builder setTextArray(
        com.nooriro.oneuplus.proto.googlecarrier.TextArray.Builder builderForValue) {
      copyOnWrite();
      instance.setTextArray(builderForValue.build());
      return this;
    }
    /**
     * <code>.TextArray textArray = 6;</code>
     */
    public Builder mergeTextArray(com.nooriro.oneuplus.proto.googlecarrier.TextArray value) {
      copyOnWrite();
      instance.mergeTextArray(value);
      return this;
    }
    /**
     * <code>.TextArray textArray = 6;</code>
     */
    public Builder clearTextArray() {
      copyOnWrite();
      instance.clearTextArray();
      return this;
    }

    /**
     * <code>.IntArray intArray = 7;</code>
     */
    @java.lang.Override
    public boolean hasIntArray() {
      return instance.hasIntArray();
    }
    /**
     * <code>.IntArray intArray = 7;</code>
     */
    @java.lang.Override
    public com.nooriro.oneuplus.proto.googlecarrier.IntArray getIntArray() {
      return instance.getIntArray();
    }
    /**
     * <code>.IntArray intArray = 7;</code>
     */
    public Builder setIntArray(com.nooriro.oneuplus.proto.googlecarrier.IntArray value) {
      copyOnWrite();
      instance.setIntArray(value);
      return this;
    }
    /**
     * <code>.IntArray intArray = 7;</code>
     */
    public Builder setIntArray(
        com.nooriro.oneuplus.proto.googlecarrier.IntArray.Builder builderForValue) {
      copyOnWrite();
      instance.setIntArray(builderForValue.build());
      return this;
    }
    /**
     * <code>.IntArray intArray = 7;</code>
     */
    public Builder mergeIntArray(com.nooriro.oneuplus.proto.googlecarrier.IntArray value) {
      copyOnWrite();
      instance.mergeIntArray(value);
      return this;
    }
    /**
     * <code>.IntArray intArray = 7;</code>
     */
    public Builder clearIntArray() {
      copyOnWrite();
      instance.clearIntArray();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:Config)
  }
  private byte memoizedIsInitialized = 2;
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new com.nooriro.oneuplus.proto.googlecarrier.Config();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "value_",
            "valueCase_",
            "bitField0_",
            "key_",
            com.nooriro.oneuplus.proto.googlecarrier.TextArray.class,
            com.nooriro.oneuplus.proto.googlecarrier.IntArray.class,
          };
          java.lang.String info =
              "\u0001\u0007\u0001\u0001\u0001\u0007\u0007\u0000\u0000\u0001\u0001\u1508\u0000\u0002" +
              "\u103b\u0000\u0003\u1037\u0000\u0004\u1035\u0000\u0005\u103a\u0000\u0006\u103c\u0000" +
              "\u0007\u103c\u0000";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<com.nooriro.oneuplus.proto.googlecarrier.Config> parser = PARSER;
        if (parser == null) {
          synchronized (com.nooriro.oneuplus.proto.googlecarrier.Config.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<com.nooriro.oneuplus.proto.googlecarrier.Config>(
                      DEFAULT_INSTANCE);
              PARSER = parser;
            }
          }
        }
        return parser;
    }
    case GET_MEMOIZED_IS_INITIALIZED: {
      return memoizedIsInitialized;
    }
    case SET_MEMOIZED_IS_INITIALIZED: {
      memoizedIsInitialized = (byte) (arg0 == null ? 0 : 1);
      return null;
    }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:Config)
  private static final com.nooriro.oneuplus.proto.googlecarrier.Config DEFAULT_INSTANCE;
  static {
    Config defaultInstance = new Config();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      Config.class, defaultInstance);
  }

  public static com.nooriro.oneuplus.proto.googlecarrier.Config getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<Config> PARSER;

  public static com.google.protobuf.Parser<Config> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

