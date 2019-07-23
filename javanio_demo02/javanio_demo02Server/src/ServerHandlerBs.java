import java.io.IOException;
import java.nio.channels.SelectionKey;

interface ServerHandlerBs {
	 void handleAccept(SelectionKey selectionKey) throws IOException;

	 String handleRead(SelectionKey selectionKey) throws IOException;
}
