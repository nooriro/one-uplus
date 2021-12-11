// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: carriersettings.proto

package com.nooriro.oneuplus.proto.googlecarrier;

/**
 * Protobuf type {@code CarrierConfig}
 */
public  final class CarrierConfig extends
    com.google.protobuf.GeneratedMessageLite<
        CarrierConfig, CarrierConfig.Builder> implements
    // @@protoc_insertion_point(message_implements:CarrierConfig)
    CarrierConfigOrBuilder {
  private CarrierConfig() {
    config_ = emptyProtobufList();
  }
  public static final int CONFIG_FIELD_NUMBER = 2;
  private com.google.protobuf.Internal.ProtobufList<com.nooriro.oneuplus.proto.googlecarrier.Config> config_;
  /**
   * <code>repeated .Config config = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.nooriro.oneuplus.proto.googlecarrier.Config> getConfigList() {
    return config_;
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  public java.util.List<? extends com.nooriro.oneuplus.proto.googlecarrier.ConfigOrBuilder> 
      getConfigOrBuilderList() {
    return config_;
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  @java.lang.Override
  public int getConfigCount() {
    return config_.size();
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  @java.lang.Override
  public com.nooriro.oneuplus.proto.googlecarrier.Config getConfig(int index) {
    return config_.get(index);
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  public com.nooriro.oneuplus.proto.googlecarrier.ConfigOrBuilder getConfigOrBuilder(
      int index) {
    return config_.get(index);
  }
  private void ensureConfigIsMutable() {
    com.google.protobuf.Internal.ProtobufList<com.nooriro.oneuplus.proto.googlecarrier.Config> tmp = config_;
    if (!tmp.isModifiable()) {
      config_ =
          com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
     }
  }

  /**
   * <code>repeated .Config config = 2;</code>
   */
  private void setConfig(
      int index, com.nooriro.oneuplus.proto.googlecarrier.Config value) {
    value.getClass();
  ensureConfigIsMutable();
    config_.set(index, value);
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  private void addConfig(com.nooriro.oneuplus.proto.googlecarrier.Config value) {
    value.getClass();
  ensureConfigIsMutable();
    config_.add(value);
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  private void addConfig(
      int index, com.nooriro.oneuplus.proto.googlecarrier.Config value) {
    value.getClass();
  ensureConfigIsMutable();
    config_.add(index, value);
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  private void addAllConfig(
      java.lang.Iterable<? extends com.nooriro.oneuplus.proto.googlecarrier.Config> values) {
    ensureConfigIsMutable();
    com.google.protobuf.AbstractMessageLite.addAll(
        values, config_);
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  private void clearConfig() {
    config_ = emptyProtobufList();
  }
  /**
   * <code>repeated .Config config = 2;</code>
   */
  private void removeConfig(int index) {
    ensureConfigIsMutable();
    config_.remove(index);
  }

  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig prototype) {
    return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code CarrierConfig}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig, Builder> implements
      // @@protoc_insertion_point(builder_implements:CarrierConfig)
      com.nooriro.oneuplus.proto.googlecarrier.CarrierConfigOrBuilder {
    // Construct using com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>repeated .Config config = 2;</code>
     */
    @java.lang.Override
    public java.util.List<com.nooriro.oneuplus.proto.googlecarrier.Config> getConfigList() {
      return java.util.Collections.unmodifiableList(
          instance.getConfigList());
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    @java.lang.Override
    public int getConfigCount() {
      return instance.getConfigCount();
    }/**
     * <code>repeated .Config config = 2;</code>
     */
    @java.lang.Override
    public com.nooriro.oneuplus.proto.googlecarrier.Config getConfig(int index) {
      return instance.getConfig(index);
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder setConfig(
        int index, com.nooriro.oneuplus.proto.googlecarrier.Config value) {
      copyOnWrite();
      instance.setConfig(index, value);
      return this;
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder setConfig(
        int index, com.nooriro.oneuplus.proto.googlecarrier.Config.Builder builderForValue) {
      copyOnWrite();
      instance.setConfig(index,
          builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder addConfig(com.nooriro.oneuplus.proto.googlecarrier.Config value) {
      copyOnWrite();
      instance.addConfig(value);
      return this;
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder addConfig(
        int index, com.nooriro.oneuplus.proto.googlecarrier.Config value) {
      copyOnWrite();
      instance.addConfig(index, value);
      return this;
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder addConfig(
        com.nooriro.oneuplus.proto.googlecarrier.Config.Builder builderForValue) {
      copyOnWrite();
      instance.addConfig(builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder addConfig(
        int index, com.nooriro.oneuplus.proto.googlecarrier.Config.Builder builderForValue) {
      copyOnWrite();
      instance.addConfig(index,
          builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder addAllConfig(
        java.lang.Iterable<? extends com.nooriro.oneuplus.proto.googlecarrier.Config> values) {
      copyOnWrite();
      instance.addAllConfig(values);
      return this;
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder clearConfig() {
      copyOnWrite();
      instance.clearConfig();
      return this;
    }
    /**
     * <code>repeated .Config config = 2;</code>
     */
    public Builder removeConfig(int index) {
      copyOnWrite();
      instance.removeConfig(index);
      return this;
    }

    // @@protoc_insertion_point(builder_scope:CarrierConfig)
  }
  private byte memoizedIsInitialized = 2;
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "config_",
            com.nooriro.oneuplus.proto.googlecarrier.Config.class,
          };
          java.lang.String info =
              "\u0001\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0001\u0001\u0002\u041b";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig> parser = PARSER;
        if (parser == null) {
          synchronized (com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig>(
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


  // @@protoc_insertion_point(class_scope:CarrierConfig)
  private static final com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig DEFAULT_INSTANCE;
  static {
    CarrierConfig defaultInstance = new CarrierConfig();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      CarrierConfig.class, defaultInstance);
  }

  public static com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<CarrierConfig> PARSER;

  public static com.google.protobuf.Parser<CarrierConfig> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}
