import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Encode {
	private int no;
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;


	@Override
	public String toString() {
		return "no=" + no + ", name=" + name ;
	}

	public static byte[]  compress( final String strTobCompressed) throws UnsupportedEncodingException, IOException {
		System.out.println("String length : " + strTobCompressed.length());
        ByteArrayOutputStream obj=new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(strTobCompressed.getBytes("UTF-8"));
        gzip.close();
        return obj.toByteArray();
	}
	public static String decompress( final byte[] StrToBdecompressed) throws IOException {
	    final StringBuilder outStr = new StringBuilder();
	    //newTry
	    ByteArrayInputStream obj1 = new ByteArrayInputStream(StrToBdecompressed);
	    final GZIPInputStream gis = new GZIPInputStream(obj1);
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis, "UTF-8"));

	      String line;
	      while ((line = bufferedReader.readLine()) != null) {
	        outStr.append(line);
	      }
	    return outStr.toString();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		// TODO Auto-generated method stub
		//encode
		List<Encode> encodeList = new ArrayList<>();
		Encode encodeObj = new Encode();
		encodeObj.setNo(1);
		encodeObj.setName("Samiksha");
		encodeList.add(encodeObj);
		System.out.println("Before List: "+encodeList.toString());
		String str1 = encodeList.toString();
		String encodedString = Base64.getEncoder().encodeToString(str1.getBytes());
		System.out.println("Encoded String :"+encodedString);
		
		//compress
		byte[] compressed = compress(encodedString);
		System.out.println("compressed string: "+compressed);
		
		//decompress
		String decompressed = decompress(compressed);
		System.out.println("decompressed string: "+decompressed);
		
		//decode
		byte[] decodedString = Base64.getDecoder().decode(decompressed);
		System.out.println("Decoded String: "+new String(decodedString));
	}

}
