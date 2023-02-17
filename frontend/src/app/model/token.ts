export interface TokenPage {
  content: Token[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Token {
  token: string;
  userId?: any;
  role: string;
  createdAt: Date;
  schoolClassName: string;
}
