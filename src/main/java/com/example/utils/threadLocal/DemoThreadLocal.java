package com.example.utils.threadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DemoThreadLocal {
	private Map<Thread,Object> localMap = Collections.synchronizedMap(new HashMap<Thread,Object>());
}
