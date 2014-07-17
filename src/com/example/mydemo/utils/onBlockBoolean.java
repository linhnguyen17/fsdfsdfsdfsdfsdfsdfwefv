package com.example.mydemo.utils;

public interface onBlockBoolean {

	void onResult(boolean result, String error);

	public void updateProgress(int byteWritten, int totalSize);
}
