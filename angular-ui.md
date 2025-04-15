Here are the steps to manually create the **Angular 17+ project** and the **Customer component**:

### Step 1: Install Angular CLI (if not installed)
First, install the Angular CLI globally (if you haven't already):
```bash
npm install -g @angular/cli
```

### Step 2: Create a new Angular project
Create a new Angular project with the following command:
```bash
ng new smart-billing-angular17 --standalone
cd smart-billing-angular17
```
This will create a new Angular project with Angular 17+ and set it up with **standalone components**.

### Step 3: Create the Customer component
Generate the Customer component inside the `src/app` folder:
```bash
ng generate component customer --standalone
```
This will create the component files, but you will need to modify them for your customer module.

### Step 4: Edit `customer.component.ts`
Open `src/app/customer/customer.component.ts` and modify it to add the logic for handling customer data:
```ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {
  customers = [];
  customer = { name: '', email: '', phone: '' };

  addCustomer() {
    this.customers.push({ ...this.customer });
    this.customer = { name: '', email: '', phone: '' };
  }
}
```

### Step 5: Edit `customer.component.html`
Open `src/app/customer/customer.component.html` and add the HTML for the customer form and customer list:
```html
<div class="form">
  <input [(ngModel)]="customer.name" placeholder="Name">
  <input [(ngModel)]="customer.email" placeholder="Email">
  <input [(ngModel)]="customer.phone" placeholder="Phone">
  <button (click)="addCustomer()">Add</button>
</div>

<ul>
  <li *ngFor="let c of customers">{{ c.name }} - {{ c.email }} - {{ c.phone }}</li>
</ul>
```

### Step 6: Edit `customer.component.css`
You can style the component by adding CSS in `src/app/customer/customer.component.css`:
```css
.form input {
  margin: 4px;
  padding: 4px;
}
```

### Step 7: Edit `app.component.ts`
Modify the root component to include the `CustomerComponent`. Open `src/app/app.component.ts` and replace its content with the following:
```ts
import { Component } from '@angular/core';
import { CustomerComponent } from './customer/customer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CustomerComponent],
  template: `
    <h1>Smart Billing Application</h1>
    <app-customer></app-customer>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent { }
```

### Step 8: Edit `index.html`
Ensure that the `index.html` file contains the root component, which is already set up by Angular CLI. The content of `src/index.html` should look like this:
```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Smart Billing</title>
  <base href="/">
</head>
<body>
  <app-root></app-root>
</body>
</html>
```

### Step 9: Run the Application
Now that the Angular app is set up, you can run the project with:
```bash
ng serve
```

Open your browser and go to `http://localhost:4200` to see the **Smart Billing Application** with the **Customer** module.

---

This should set up a simple UI for managing customers. If you need further adjustments or additions, let me know!