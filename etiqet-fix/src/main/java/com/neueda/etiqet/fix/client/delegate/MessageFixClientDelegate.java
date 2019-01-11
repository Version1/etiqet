package com.neueda.etiqet.fix.client.delegate;

import com.neueda.etiqet.core.client.delegate.BaseClientDelegate;
import com.neueda.etiqet.core.client.delegate.ClientDelegate;
import com.neueda.etiqet.core.message.cdr.Cdr;

/**
 * Delegate for QuickFix client that fills some necessary parameters when sending messages to the server.
 */
public class MessageFixClientDelegate extends BaseClientDelegate {

    static final String FIELD_SEPARATOR = "\u0001";
    static final String KEY_VALUE_SEPARATOR = "=";

    /** The message containing the values to be overwritten when intercepting the send operation. */
    protected Cdr message;

    /**
     * Constructor.
     */
    public MessageFixClientDelegate() {
        super();
    }

    /**
     * Constructor.
     *
     * @param next the next delegate on the chain to process the message.
     */
    public MessageFixClientDelegate(ClientDelegate next) {
        super(next);
    }

    /**
     * Sets the message with the parameter values to be replaced just before send.
     * @param msg the message containing the values by default.
     */
    public void setMessage(Cdr msg) {
        this.message = msg;
    }

    @Override
    public Cdr processMessage(Cdr msg) {
        if(next instanceof MessageFixClientDelegate) {
            ((MessageFixClientDelegate)next).setMessage(message);
        }
        message = null;
        return super.processMessage(msg);
    }
}
