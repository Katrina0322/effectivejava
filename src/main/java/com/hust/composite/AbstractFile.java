package com.hust.composite;

import java.util.ArrayList;
import java.util.List;

public interface AbstractFile {
	void killVirus();

}

class ImageFile implements AbstractFile{
	private String name;

	public ImageFile(String name) {
		super();
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("查杀"+ name +"图像文件");
	}
	
}

class TextFile implements AbstractFile{
	private String name;

	public TextFile(String name) {
		super();
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("查杀"+ name +"图像文件");
	}
	
}


class Folder implements AbstractFile{
	private String name;
	private List<AbstractFile> list = new ArrayList<AbstractFile>();
	
	public Folder(String name) {
		super();
		this.name = name;
	}
	
	public void add(AbstractFile file){
		list.add(file);
	}
	
	public void remove(AbstractFile file){
		list.remove(file);
	}
	
	public AbstractFile getChild(int index){
		return list.get(index);
	}

	@Override
	public void killVirus() {
		System.out.println("文件夹"+name+"进行查杀");
		for(AbstractFile file:list){
			file.killVirus();
		}
	}
}
