import React, {Component} from 'react';




class App extends Component {
	
constructor(props) {
    super(props);
    this.state = {
      books: [],
	  name: '',
      author: '',
      year: ''
    };
	
    this.create = this.create.bind(this);
    this.update = this.update.bind(this);
    this.delete = this.delete.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }
  
  
  
  componentDidMount() {
    fetch('http://localhost:8080/books')
            .then(res => res.json())
            .then((data) => {
                this.setState({ books: data })
            })
            .catch(console.log)
  }
  
  
  create(e) {
    e.preventDefault();
	const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
								   name: this.state.name,
								   author: this.state.author,
								   year: this.state.year })
        };
        fetch('http://localhost:8080/books/add', requestOptions)
            .then(response => response.json())
            .then((data) => {
				console.log(data)
                this.setState({ books: data })
            })
            .catch(console.log)
  }
  
  
  update(e) {
    e.preventDefault();
	const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
								   name: this.state.name,
								   author: this.state.author,
								   year: this.state.year })
        };
        fetch('http://localhost:8080/books/update', requestOptions)
            .then(response => response.json())
            .then((data) => {
				console.log(data)
                this.setState({ books: data })
            })
            .catch(console.log)
  }
  
  delete(e) {
	 fetch('http://localhost:8080/books/delete', { method: 'DELETE' })
			.then(response => response.json())
            .then((data) => {
				console.log(data)
                this.setState({ books: data })
            })
            .catch(console.log)
  }
  
  
  handleChange(changeObject) {
    this.setState(changeObject)
  }
  
  
  render() {
    return (
	
		<div className="container">	
		
			<h2>Book List</h2>
		
			<div>
                <label htmlFor="name">
                  Book name:
                  <input
                    name="name"
                    id="name"
                    type="text"
                    className="form-control"
                    value={this.state.name}
                    onChange={(e) => this.handleChange({ name: e.target.value })}
                    required
                    />
                </label>
                <label htmlFor="author">
                  Author:
                  <input
                    name="author"
                    id="author"
                    type="text"
                    className="form-control"
                    value={this.state.author}
                    onChange={(e) => this.handleChange({ author: e.target.value })}
                    required
                    />
                </label>
                <label htmlFor="year">
                  Publishing year:
                  <input
                    name="year"
                    id="year"
                    type="text"
                    className="form-control"
                    value={this.state.year}
                    onChange={(e) => this.handleChange({ year: e.target.value })}
                    />
                </label>

		<button className="btn btn-dark" type='button' onClick={(e) => this.create(e)}>
           Add
        </button>
				
			</div>	
				
			<table className="table table-dark table-striped">
			<thead>
			  <tr>
				<th>Book Name</th>
				<th>Author</th>
				<th>Publishing Year</th>
			  </tr>
			</thead>
			<tbody>
			
			{this.state.books && this.state.books.map((book) => (
			  <tr key={book.id}>
				<td>{book.name}</td>
				<td>{book.author}</td>
				<td>{book.year}</td>
				<td>
				<button className="btn btn-dark" type='button' onClick={(e) => this.delete(book.id)}>
				Delete
				</button>
				<button className="btn btn-dark" type='button' onClick={(e) => this.update(e)}>
			Update
				</button>
				</td>
			  </tr>
			  ))}
			</tbody>
		  </table>	
		  
		  
		  
		  
		  
		</div>
    );
  }
}

export default App;