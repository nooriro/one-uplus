// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: carriersettings.proto

package com.nooriro.oneuplus.proto.googlecarrier;

/**
 * Protobuf type {@code VendorConfigs}
 */
public  final class VendorConfigs extends
    com.google.protobuf.GeneratedMessageLite<
        VendorConfigs, VendorConfigs.Builder> implements
    // @@protoc_insertion_point(message_implements:VendorConfigs)
    VendorConfigsOrBuilder {
  private VendorConfigs() {
    client_ = emptyProtobufList();
  }
  public static final int CLIENT_FIELD_NUMBER = 2;
  private com.google.protobuf.Internal.ProtobufList<com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient> client_;
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient> getClientList() {
    return client_;
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  public java.util.List<? extends com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClientOrBuilder> 
      getClientOrBuilderList() {
    return client_;
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  @java.lang.Override
  public int getClientCount() {
    return client_.size();
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  @java.lang.Override
  public com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient getClient(int index) {
    return client_.get(index);
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  public com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClientOrBuilder getClientOrBuilder(
      int index) {
    return client_.get(index);
  }
  private void ensureClientIsMutable() {
    com.google.protobuf.Internal.ProtobufList<com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient> tmp = client_;
    if (!tmp.isModifiable()) {
      client_ =
          com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
     }
  }

  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  private void setClient(
      int index, com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient value) {
    value.getClass();
  ensureClientIsMutable();
    client_.set(index, value);
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  private void addClient(com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient value) {
    value.getClass();
  ensureClientIsMutable();
    client_.add(value);
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  private void addClient(
      int index, com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient value) {
    value.getClass();
  ensureClientIsMutable();
    client_.add(index, value);
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  private void addAllClient(
      java.lang.Iterable<? extends com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient> values) {
    ensureClientIsMutable();
    com.google.protobuf.AbstractMessageLite.addAll(
        values, client_);
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  private void clearClient() {
    client_ = emptyProtobufList();
  }
  /**
   * <code>repeated .VendorConfigClient client = 2;</code>
   */
  private void removeClient(int index) {
    ensureClientIsMutable();
    client_.remove(index);
  }

  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs prototype) {
    return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code VendorConfigs}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs, Builder> implements
      // @@protoc_insertion_point(builder_implements:VendorConfigs)
      com.nooriro.oneuplus.proto.googlecarrier.VendorConfigsOrBuilder {
    // Construct using com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    @java.lang.Override
    public java.util.List<com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient> getClientList() {
      return java.util.Collections.unmodifiableList(
          instance.getClientList());
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    @java.lang.Override
    public int getClientCount() {
      return instance.getClientCount();
    }/**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    @java.lang.Override
    public com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient getClient(int index) {
      return instance.getClient(index);
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder setClient(
        int index, com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient value) {
      copyOnWrite();
      instance.setClient(index, value);
      return this;
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder setClient(
        int index, com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient.Builder builderForValue) {
      copyOnWrite();
      instance.setClient(index,
          builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder addClient(com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient value) {
      copyOnWrite();
      instance.addClient(value);
      return this;
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder addClient(
        int index, com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient value) {
      copyOnWrite();
      instance.addClient(index, value);
      return this;
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder addClient(
        com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient.Builder builderForValue) {
      copyOnWrite();
      instance.addClient(builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder addClient(
        int index, com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient.Builder builderForValue) {
      copyOnWrite();
      instance.addClient(index,
          builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder addAllClient(
        java.lang.Iterable<? extends com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient> values) {
      copyOnWrite();
      instance.addAllClient(values);
      return this;
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder clearClient() {
      copyOnWrite();
      instance.clearClient();
      return this;
    }
    /**
     * <code>repeated .VendorConfigClient client = 2;</code>
     */
    public Builder removeClient(int index) {
      copyOnWrite();
      instance.removeClient(index);
      return this;
    }

    // @@protoc_insertion_point(builder_scope:VendorConfigs)
  }
  private byte memoizedIsInitialized = 2;
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "client_",
            com.nooriro.oneuplus.proto.googlecarrier.VendorConfigClient.class,
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
        com.google.protobuf.Parser<com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs> parser = PARSER;
        if (parser == null) {
          synchronized (com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs>(
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


  // @@protoc_insertion_point(class_scope:VendorConfigs)
  private static final com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs DEFAULT_INSTANCE;
  static {
    VendorConfigs defaultInstance = new VendorConfigs();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      VendorConfigs.class, defaultInstance);
  }

  public static com.nooriro.oneuplus.proto.googlecarrier.VendorConfigs getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<VendorConfigs> PARSER;

  public static com.google.protobuf.Parser<VendorConfigs> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

