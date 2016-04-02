package com.alesegdia.famjam6.util;


import java.util.ArrayList;
import java.util.List;

public class Matrix2D <T> {
	
	protected ArrayList<T> data = null;
	public int cols;
	public int rows;
	
	public Matrix2D( int cols, int rows, T def )
	{
		this.data = new ArrayList<T>();
		this.fill(def);
		this.rows = rows;
		this.cols = cols;
	}
	
	public Matrix2D( Matrix2D<T> other )
	{
		this.data = new ArrayList<T>(other.data);
		this.rows = other.rows;
		this.cols = other.cols;
	}
	
	public Matrix2D( List<T> data, int cols, int rows )
	{
		this.createFromData(data, cols, rows);
	}
	
	private void createFromData(List<T> data, int cols, int rows) {
		this.data = new ArrayList<T>(data);
		this.rows = rows;
		this.cols = cols;
	}

	protected Matrix2D() {
		// TODO Auto-generated constructor stub
	}

	public T get( int col, int row )
	{
		if( row < 0 ) row = 0;
		if( row >= rows ) row = rows - 1;
		if( col < 0 ) col = 0;
		if( col >= cols ) col = cols - 1;
		
		return data.get( getPos( col, row ) );
	}
	
	private int getPos( int col, int row )
	{
		return row * this.cols + col;
	}
	
	public void Set( int col, int row, T val )
	{
		if( row < 0 ) row = 0;
		if( row >= rows ) row = rows - 1;
		if( col < 0 ) col = 0;
		if( col >= cols ) col = cols - 1;
		
		data.set( getPos( col, row ), val );
	}
	
	public void fill( T val )
	{
		for( int i = 0; i < data.size(); i++ )
		{
			data.set(i, val);
		}
	}
	
	@Override
	public String toString()
	{
		String s = "";
		
		for( int i = 0; i < this.cols; i++ )
		{
			for( int j = 0; j < this.rows; j++ )
			{
				s += "\t" + this.get(i, j);
			}
			s += "\n";
		}
		
		return s;
	}


}
