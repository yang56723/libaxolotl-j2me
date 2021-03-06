/**
 * Copyright (C) 2013 Open Whisper Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.whispersystems.libaxolotl;

import org.whispersystems.libaxolotl.ecc.Curve;
import org.whispersystems.libaxolotl.ecc.ECPrivateKey;
import org.whispersystems.libaxolotl.state.protos.IdentityKeyPairStructure;


/**
 * Holder for public and private identity key pair.
 *
 * @author Moxie Marlinspike
 */
public class IdentityKeyPair {

  private final IdentityKey  publicKey;
  private final ECPrivateKey privateKey;

  public IdentityKeyPair(IdentityKey publicKey, ECPrivateKey privateKey) {
    this.publicKey  = publicKey;
    this.privateKey = privateKey;
  }

  public IdentityKeyPair(byte[] serialized) throws InvalidKeyException {
    IdentityKeyPairStructure structure = IdentityKeyPairStructure.fromBytes(serialized);
    this.publicKey  = new IdentityKey(structure.getPublickey(), 0);
    this.privateKey = Curve.decodePrivatePoint(structure.getPrivatekey());
  }

  public IdentityKey getPublicKey() {
    return publicKey;
  }

  public ECPrivateKey getPrivateKey() {
    return privateKey;
  }

  public byte[] serialize() {
    IdentityKeyPairStructure structure = new IdentityKeyPairStructure();
    structure.setPublickey(publicKey.serialize());
    structure.setPrivatekey(privateKey.serialize());

    return structure.toBytes();
  }
}
