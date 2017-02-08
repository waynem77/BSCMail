/*
 * Copyright © 2017 its authors.  See the file "AUTHORS" for details.
 *
 * This file is part of BSCMail.
 *
 * BSCMail is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BSCMail is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BSCMail.  If not, see <http://www.gnu.org/licenses/>.
 */

package bscmail;

import iolayer.IOLayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.ReadWritable;


/**
 * A non-persistent in-memory I/O layer used in unit tests.
 *
 * @author Wayne Miller
 * @param <T> the type of read-writable managed by this I/O layer
 * @since 3.0
 */
public class TestIOLayer<T extends ReadWritable> implements IOLayer<T> {

    /**
     * The list of read-writeables.
     */
    private List<T> list;

    public TestIOLayer() {
        list = new ArrayList<>();
    }    // TestIOLayer()

    /**
     * {@inheritDoc}
     * @return
     * @throws IOException never
     */
    @Override
    public List<T> getAll() throws IOException {
        assertInvariant();
        return list;
    }    // getAll()

    /**
     * {@inheritDoc}
     * @param list
     */
    @Override
    public void setAll(List<T> list) {
        assertInvariant();
        if (list == null) {
            throw new NullPointerException("list may not be null");
        }    // if
        if (list.contains(null)) {
            throw new NullPointerException("list may not contain null");
        }    // if
        this.list = list;
        assertInvariant();
    }    // setAll()

    /**
     * Asserts that the internal state of the object is correct.
     */
    private void assertInvariant() {
        assert (list != null);
        assert (! list.contains(null));
    }    //assertInvariant()

}    // TestIOLayer
