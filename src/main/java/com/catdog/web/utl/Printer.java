package com.catdog.web.utl;

import java.util.function.Consumer;

import org.springframework.stereotype.Service;
import com.catdog.web.cmm.IConsumer;
@Service
public class Printer implements IConsumer{

	@Override
	public void accept(Object o) {
		// TODO Auto-generated method stub
		Consumer<String> c = System.out :: println;
		c.accept((String)o);
	}
	
}
