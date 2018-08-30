/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.bhm.sdk.bhmlibrary.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.webkit.MimeTypeMap;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHelper {

	private static final String _DATA = "_data";

	/**
	 * Returns the real path of the given URI string. If the given URI string
	 * represents a content:// URI, the real path is retrieved from the media
	 * store.
	 *
	 * @param uriString
	 *            the URI string of the audio/image/video
	 *            the current application context
	 * @return the full path to the file
	 */
	@SuppressWarnings("deprecation")
	public static String getRealPath(String uriString, Activity ctx) {
		String realPath = null;

		if (uriString.startsWith("content://")) {
			String[] proj = { _DATA };
			Cursor cursor = ctx.managedQuery(Uri.parse(uriString), proj, null,
					null, null);
			if (null != cursor) {

				int column_index = cursor.getColumnIndexOrThrow(_DATA);
				cursor.moveToFirst();
				realPath = cursor.getString(column_index);
			}
			if (realPath == null) {
				// LOG.e(LOG_TAG, "Could get real path for URI string %s",
				// uriString);
			}
		} else if (uriString.startsWith("file://")) {
			realPath = uriString.substring(7);
			if (realPath.startsWith("/android_asset/")) {
				realPath = null;
			}
		} else {
			realPath = uriString;
		}

		return realPath;
	}

	/**
	 * Returns the real path of the given URI. If the given URI is a content://
	 * URI, the real path is retrieved from the media store.
	 *
	 * @param uri
	 *            the URI of the audio/image/video
	 *            the current application context
	 * @return the full path to the file
	 */
	public static String getRealPath(Uri uri, Activity ctx) {
		return FileHelper.getRealPath(uri.toString(), ctx);
	}

	/**
	 * Returns an input stream based on given URI string.
	 *
	 * @param uriString
	 *            the URI string from which to obtain the input stream
	 *            the current application context
	 * @return an input stream into the data at the given URI or null if given
	 *         an invalid URI string
	 * @throws IOException
	 */
	public static InputStream getInputStreamFromUriString(String uriString,
														  Activity ctx) throws IOException {
		if (uriString.startsWith("content")) {
			Uri uri = Uri.parse(uriString);
			return ctx.getContentResolver().openInputStream(uri);
		} else if (uriString.startsWith("file:///android_asset/")) {
			Uri uri = Uri.parse(uriString);
			String relativePath = uri.getPath().substring(15);
			/*String relateivPathArags[]=relativePath.split("/");
			
			String s1=relateivPathArags[0];
			String s2=relateivPathArags[1];
			
			String relativePath1=s1+"/page"+"/"+s2;*/

			return ctx.getAssets().open(relativePath);
		} else {
			return new FileInputStream(getRealPath(uriString, ctx));
		}
	}

	/**
	 * Removes the "file://" prefix from the given URI string, if applicable. If
	 * the given URI string doesn't have a "file://" prefix, it is returned
	 * unchanged.
	 *
	 * @param uriString
	 *            the URI string to operate on
	 * @return a path without the "file://" prefix
	 */
	public static String stripFileProtocol(String uriString) {
		if (uriString.startsWith("file://")) {
			uriString = uriString.substring(7);
		}
		return uriString;
	}

	/**
	 * Returns the mime type of the data specified by the given URI string.
	 *
	 * @param uriString
	 *            the URI string of the data
	 * @return the mime type of the specified data
	 */
	@SuppressLint("DefaultLocale")
	public static String getMimeType(String uriString, Activity ctx) {
		String mimeType = null;

		Uri uri = Uri.parse(uriString);
		if (uriString.startsWith("content://")) {
			mimeType = ctx.getContentResolver().getType(uri);
		} else {
			// MimeTypeMap.getFileExtensionFromUrl() fails when there are query
			// parameters.
			String extension = uri.getPath();
			int lastDot = extension.lastIndexOf('.');
			if (lastDot != -1) {
				extension = extension.substring(lastDot + 1);
			}
			// Convert the URI string to lower case to ensure compatibility with
			// MimeTypeMap (see CB-2185).
			extension = extension.toLowerCase();
			if ("3ga".equals(extension)) {
				mimeType = "audio/3gpp";
			} else {
				mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
						extension);
			}
		}
		return mimeType;
	}

	/**
	 * 关闭IO
	 *
	 * @param closeables closeable
	 */
	public static void closeIO(Closeable... closeables) {
		if (closeables == null) return;
		try {
			for (Closeable closeable : closeables) {
				if (closeable != null) {
					closeable.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断文件是否存在，不存在则判断是否创建成功
	 *
	 * @param file 文件
	 * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
	 */
	public static boolean createOrExistsFile(File file) {
		if (file == null) return false;
		// 如果存在，是文件则返回true，是目录则返回false
		if (file.exists()) return file.isFile();
		if (!createOrExistsDir(file.getParentFile())) return false;
		try {
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断目录是否存在，不存在则判断是否创建成功
	 *
	 * @param file 文件
	 * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
	 */
	public static boolean createOrExistsDir(File file) {
		// 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
		return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
	}

	/**
	 * 得到SD卡根目录.
	 */
	public static File getRootPath() {
		File path = null;
		if (sdCardIsAvailable()) {
			path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
		} else {
			path = Environment.getDataDirectory();
		}
		return path;
	}

	/**
	 * 获取的目录默认没有最后的”/”,需要自己加上
	 * 获取本应用图片缓存目录
	 *
	 * @return
	 */
	public static File getCecheFolder(Context context) {
		File folder = new File(context.getCacheDir(), "IMAGECACHE");
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}

	/**
	 * 判断SD卡是否可用
	 *
	 * @return true : 可用<br>false : 不可用
	 */
	public static boolean isSDCardEnable() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	/**
	 * 获取SD卡路径
	 * <p>一般是/storage/emulated/0/</p>
	 *
	 * @return SD卡路径
	 */
	public static String getSDCardPath() {
		if (!isSDCardEnable()) return "sdcard unable!";
		return Environment.getExternalStorageDirectory().getPath() + File.separator;
	}

	/**
	 * 获取SD卡Data路径
	 *
	 * @return SD卡Data路径
	 */
	public static String getDataPath() {
		if (!isSDCardEnable()) return "sdcard unable!";
		return Environment.getDataDirectory().getPath();
	}

	/**
	 * 获取SD卡剩余空间
	 *
	 * @return SD卡剩余空间
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	public static String getFreeSpace() {
		if (!isSDCardEnable()) return "sdcard unable!";
		StatFs stat = new StatFs(getSDCardPath());
		long blockSize, availableBlocks;
		availableBlocks = stat.getAvailableBlocksLong();
		blockSize = stat.getBlockSizeLong();
		return TextUtils.byte2FitSize(availableBlocks * blockSize);
	}

	/**
	 * SD卡是否可用.
	 */
	public static boolean sdCardIsAvailable() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File sd = new File(Environment.getExternalStorageDirectory().getPath());
			return sd.canWrite();
		} else
			return false;
	}

	/**
	 * 文件或者文件夹是否存在.
	 */
	public static boolean fileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 删除指定文件夹下所有文件, 不保留文件夹.
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (file.isFile()) {
			file.delete();
			return true;
		}
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File exeFile = files[i];
			if (exeFile.isDirectory()) {
				delAllFile(exeFile.getAbsolutePath());
			} else {
				exeFile.delete();
			}
		}
		file.delete();

		return flag;
	}

	/**
	 * 删除目录下的所有文件
	 *
	 * @param dirPath 目录路径
	 * @return {@code true}: 删除成功<br>{@code false}: 删除失败
	 */
	public static boolean deleteFilesInDir(String dirPath) {
		return deleteFilesInDir(new File(dirPath));
	}

	/**
	 * 删除目录下的所有文件
	 *
	 * @param dir 目录
	 * @return {@code true}: 删除成功<br>{@code false}: 删除失败
	 */
	public static boolean deleteFilesInDir(File dir) {
		if (dir == null) return false;
		// 目录不存在返回true
		if (!dir.exists()) return true;
		// 不是目录返回false
		if (!dir.isDirectory()) return false;
		// 现在文件存在且是文件夹
		File[] files = dir.listFiles();
		if (files != null && files.length != 0) {
			for (File file : files) {
				if (file.isFile()) {
					if (!deleteFile(file)) return false;
				} else if (file.isDirectory()) {
					if (!deleteDir(file)) return false;
				}
			}
		}
		return true;
	}

	/**
	 * 删除目录
	 *
	 * @param dir 目录
	 * @return {@code true}: 删除成功<br>{@code false}: 删除失败
	 */
	public static boolean deleteDir(File dir) {
		if (dir == null) return false;
		// 目录不存在返回true
		if (!dir.exists()) return true;
		// 不是目录返回false
		if (!dir.isDirectory()) return false;
		// 现在文件存在且是文件夹
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				if (!deleteFile(file)) return false;
			} else if (file.isDirectory()) {
				if (!deleteDir(file)) return false;
			}
		}
		return dir.delete();
	}

	/**
	 * 删除文件
	 *
	 * @param srcFilePath 文件路径
	 * @return {@code true}: 删除成功<br>{@code false}: 删除失败
	 */
	public static boolean deleteFile(String srcFilePath) {
		return deleteFile(new File(srcFilePath));
	}

	/**
	 * 删除文件
	 *
	 * @param file 文件
	 * @return {@code true}: 删除成功<br>{@code false}: 删除失败
	 */
	public static boolean deleteFile(File file) {
		return file != null && (!file.exists() || file.isFile() && file.delete());
	}

	/**
	 * 清除内部缓存
	 * <p>/data/data/com.xxx.xxx/cache</p>
	 *
	 * @return {@code true}: 清除成功<br>{@code false}: 清除失败
	 */
	public static boolean cleanInternalCache(Context context) {
		return deleteFilesInDir(context.getCacheDir());
	}

	/**
	 * 清除内部文件
	 * <p>/data/data/com.xxx.xxx/files</p>
	 *
	 * @return {@code true}: 清除成功<br>{@code false}: 清除失败
	 */
	public static boolean cleanInternalFiles(Context context) {
		return deleteFilesInDir(context.getFilesDir());
	}

	/**
	 * 清除内部数据库
	 * <p>/data/data/com.xxx.xxx/databases</p>
	 *
	 * @return {@code true}: 清除成功<br>{@code false}: 清除失败
	 */
	public static boolean cleanInternalDbs(Context context) {
		return deleteFilesInDir(context.getFilesDir().getParent() + File.separator + "databases");
	}

	/**
	 * 根据名称清除数据库
	 * <p>/data/data/com.xxx.xxx/databases/dbName</p>
	 *
	 * @param dbName 数据库名称
	 * @return {@code true}: 清除成功<br>{@code false}: 清除失败
	 */
	public static boolean cleanInternalDbByName(Context context, String dbName) {
		return context.deleteDatabase(dbName);
	}

	/**
	 * 清除内部SP
	 * <p>/data/data/com.xxx.xxx/shared_prefs</p>
	 *
	 * @return {@code true}: 清除成功<br>{@code false}: 清除失败
	 */
	public static boolean cleanInternalSP(Context context) {
		return deleteFilesInDir(context.getFilesDir().getParent() + File.separator + "shared_prefs");
	}

	/**
	 * 清除外部缓存
	 * <p>/storage/emulated/0/android/data/com.xxx.xxx/cache</p>
	 *
	 * @return {@code true}: 清除成功<br>{@code false}: 清除失败
	 */
	public static boolean cleanExternalCache(Context context) {
		return isSDCardEnable() && deleteFilesInDir(context.getExternalCacheDir());
	}

	/**
	 * 清除自定义目录下的文件
	 *
	 * @param dirPath 目录路径
	 * @return {@code true}: 清除成功<br>{@code false}: 清除失败
	 */
	public static boolean cleanCustomCache(String dirPath) {
		return deleteFilesInDir(dirPath);
	}

	/**
	 * 清除自定义目录下的文件
	 *
	 * @param dir 目录
	 * @return {@code true}: 清除成功<br>{@code false}: 清除失败
	 */
	public static boolean cleanCustomCache(File dir) {
		return deleteFilesInDir(dir);
	}

	/**
	 * 文件复制.
	 */
	public static boolean copy(String srcFile, String destFile) {
		try {
			FileInputStream in = new FileInputStream(srcFile);
			FileOutputStream out = new FileOutputStream(destFile);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = in.read(bytes)) != -1) {
				out.write(bytes, 0, c);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 复制整个文件夹内.
	 *
	 * @param oldPath string 原文件路径如：c:/fqf.
	 * @param newPath string 复制后路径如：f:/fqf/ff.
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (NullPointerException e) {
		} catch (Exception e) {
		}
	}

	/**
	 * 重命名文件.
	 */
	public static boolean renameFile(String resFilePath, String newFilePath) {
		File resFile = new File(resFilePath);
		File newFile = new File(newFilePath);
		return resFile.renameTo(newFile);
	}

	/**
	 * 获取文件或者文件夹大小.
	 */
	public static long getFileAllSize(String path) {
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] childrens = file.listFiles();
				long size = 0;
				for (File f : childrens) {
					size += getFileAllSize(f.getPath());
				}
				return size;
			} else {
				return file.length();
			}
		} else {
			return 0;
		}
	}

	/**
	 * 创建一个文件.
	 */
	public static boolean initFile(String path) {
		boolean result = false;
		try {
			File file = new File(path);
			if (!file.exists()) {
				result = file.createNewFile();
			} else if (file.isDirectory()) {
				file.delete();
				result = file.createNewFile();
			} else if (file.exists()) {
				result = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 创建一个文件夹.
	 */
	public static boolean initDirectory(String path) {
		boolean result = false;
		File file = new File(path);
		if (!file.exists()) {
			result = file.mkdir();
		} else if (!file.isDirectory()) {
			file.delete();
			result = file.mkdir();
		} else if (file.exists()) {
			result = true;
		}
		return result;
	}

	/**
	 * 复制文件.
	 */
	public static void copyFile(File from, File to) throws IOException {
		if (!from.exists()) {
			throw new IOException("The source file not exist: " + from.getAbsolutePath());
		}
		FileInputStream fis = new FileInputStream(from);
		try {
			copyFile(fis, to);
		} finally {
			fis.close();
		}
	}

	/**
	 * 从InputStream流复制文件.
	 */
	public static long copyFile(InputStream from, File to) throws IOException {
		long totalBytes = 0;
		FileOutputStream fos = new FileOutputStream(to, false);
		try {
			byte[] data = new byte[1024];
			int len;
			while ((len = from.read(data)) > -1) {
				fos.write(data, 0, len);
				totalBytes += len;
			}
			fos.flush();
		} finally {
			fos.close();
		}
		return totalBytes;
	}

	/**
	 * 保存InputStream流到文件.
	 */
	public static void saveFile(InputStream inputStream, String filePath) {
		try {
			OutputStream outputStream = new FileOutputStream(new File(filePath), false);
			int len;
			byte[] buffer = new byte[1024];
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
