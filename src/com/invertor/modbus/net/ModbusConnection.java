package com.invertor.modbus.net;

import com.invertor.modbus.exception.ModbusIOException;
import com.invertor.modbus.net.stream.base.ModbusInputStream;
import com.invertor.modbus.net.stream.base.ModbusOutputStream;
import com.invertor.modbus.net.transport.ModbusTransport;

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
abstract public class ModbusConnection {

    abstract public ModbusOutputStream getOutputStream();

    abstract public ModbusInputStream getInputStream();

    abstract public ModbusTransport getTransport();

    abstract public void open() throws ModbusIOException;

    abstract public void close() throws ModbusIOException;

    abstract public void reset() throws ModbusIOException;

    abstract public void setReadTimeout(int timeout);

    @Override
    protected void finalize()
            throws Throwable {
        super.finalize();
        close();
    }
}
