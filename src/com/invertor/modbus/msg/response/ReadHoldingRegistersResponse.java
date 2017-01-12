package com.invertor.modbus.msg.response;

import com.invertor.modbus.Modbus;
import com.invertor.modbus.exception.ModbusNumberException;
import com.invertor.modbus.msg.base.AbstractReadResponse;
import com.invertor.modbus.net.stream.base.ModbusInputStream;
import com.invertor.modbus.net.stream.base.ModbusOutputStream;
import com.invertor.modbus.utils.DataUtils;
import com.invertor.modbus.utils.ModbusFunctionCode;

import java.io.IOException;

/*
 * Copyright (C) 2016 "Invertor" Factory", JSC
 * [http://www.sbp-invertor.ru]
 *
 * This file is part of JLibModbus.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: Vladislav Y. Kochedykov, software engineer.
 * email: vladislav.kochedykov@gmail.com
 */
public class ReadHoldingRegistersResponse extends AbstractReadResponse {

    private int[] registers;

    public ReadHoldingRegistersResponse(int serverAddress) throws ModbusNumberException {
        super(serverAddress);
    }

    final public int[] getRegisters() {
        return registers;
    }

    final public void setRegisters(int[] registers) throws ModbusNumberException {
        this.registers = registers;
        setByteCount(registers.length * 2);
    }

    @Override
    final protected void readData(ModbusInputStream fifo) throws IOException {
        byte[] buffer = new byte[getByteCount()];
        int size;
        if ((size = fifo.read(buffer)) < buffer.length)
            Modbus.log().warning(buffer.length + " bytes expected, but " + size + " received.");
        registers = DataUtils.toIntArray(buffer);
    }

    @Override
    final protected void writeData(ModbusOutputStream fifo) throws IOException {
        fifo.write(DataUtils.toByteArray(registers));
    }

    @Override
    public int getFunction() {
        return ModbusFunctionCode.READ_HOLDING_REGISTERS.toInt();
    }
}
