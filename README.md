# AES Encryption and Decryption bit by bit Using GUI

## Overview
This document provides a detailed explanation of the AES (Advanced Encryption Standard) encryption and decryption process for key sizes of 128, 192, and 256 bits. The source code provided implements the AES algorithm, which is a symmetric key encryption algorithm widely used for securing data. 

## AES Algorithm
AES is a block cipher that encrypts and decrypts data in blocks of 128 bits using cryptographic keys of 128, 192, or 256 bits. The algorithm consists of multiple rounds of processing, including substitution, permutation, and mixing of the input plaintext to produce the ciphertext. The number of rounds depends on the key size:

- **128-bit key:** 10 rounds
- **192-bit key:** 12 rounds
- **256-bit key:** 14 rounds

## Key Expansion
The key expansion process generates a series of round keys from the initial encryption key. These round keys are used in each round of the AES algorithm. The key expansion involves the following steps:

1. **RotWord:** A cyclic shift of the bytes in a word.
2. **SubWord:** Substitution of each byte using the AES S-box.
3. **Rcon:** Addition of a round constant to the result of the previous steps.

## Encryption Process
The encryption process consists of the following steps:

### Initial Round
- **AddRoundKey:** XOR the plaintext with the initial round key.

### Main Rounds (9, 11, or 13 rounds depending on key size)
1. **SubBytes:** Substitute each byte using the AES S-box.
2. **ShiftRows:** Shift the rows of the state matrix.
3. **MixColumns:** Mix the columns of the state matrix.
4. **AddRoundKey:** XOR the state with the round key.

### Final Round
1. **SubBytes:** Substitute each byte using the AES S-box.
2. **ShiftRows:** Shift the rows of the state matrix.
3. **AddRoundKey:** XOR the state with the final round key.

## Decryption Process
The decryption process is the inverse of the encryption process and consists of the following steps:

### Initial Round
- **AddRoundKey:** XOR the ciphertext with the final round key.

### Main Rounds (9, 11, or 13 rounds depending on key size)
1. **InvShiftRows:** Inverse shift of the rows of the state matrix.
2. **InvSubBytes:** Inverse substitution of each byte using the AES inverse S-box.
3. **AddRoundKey:** XOR the state with the round key.
4. **InvMixColumns:** Inverse mix of the columns of the state matrix.

### Final Round
1. **InvShiftRows:** Inverse shift of the rows of the state matrix.
2. **InvSubBytes:** Inverse substitution of each byte using the AES inverse S-box.
3. **AddRoundKey:** XOR the state with the initial round key.

# Demo
## Encryption process
![Encryption Process](screenshot/aes1.png?raw=true)

## Decryption process
![Encryption Process](screenshot/aes2.png?raw=true)

## Conclusion
This document outlines the AES encryption and decryption processes, providing a comprehensive overview of the algorithm’s structure and operations. For implementation details, refer to the source code provided.
