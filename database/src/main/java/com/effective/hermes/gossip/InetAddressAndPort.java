package com.effective.hermes.gossip;

import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * filename: InetAddressAndPort
 * Description:
 * Author: ubuntu
 * Date: 3/2/18 10:52 AM
 */
public class InetAddressAndPort implements Comparable<InetAddressAndPort>, Serializable {
    private static final long serialVersionUID = 2569053872331841853L;
    private static volatile int defaultPort = 7000;
    private final InetAddress address;
    private final byte[] addressBytes;
    private final int port;

    private InetAddressAndPort(InetAddress address, byte[] addressBytes, int port){
        Preconditions.checkNotNull(address);
        Preconditions.checkNotNull(addressBytes);
        validatePortRange(port);
        this.address = address;
        this.port = port;
        this.addressBytes = addressBytes;
    }

    public InetAddress getAddress() {
        return address;
    }

    public byte[] getAddressBytes() {
        return addressBytes;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InetAddressAndPort that = (InetAddressAndPort) o;

        return port == that.port && address.equals(that.address);

    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(addressBytes);
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return address.toString();
    }

    private void validatePortRange(int port) {
        if (port < 0 | port > 65535)
        {
            throw new IllegalArgumentException("Port " + port + " is not a valid port number in the range 0-65535");
        }
    }

    @Override
    public int compareTo(InetAddressAndPort o) {

        return 0;
    }
}
